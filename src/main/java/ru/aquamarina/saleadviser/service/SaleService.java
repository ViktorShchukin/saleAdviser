package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.core.model.SaleInMonth;
import ru.aquamarina.saleadviser.repository.SaleInMonthRepository;
import ru.aquamarina.saleadviser.repository.SaleRepository;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaleService {

//    private final EntityManager entityManager;
//    private final static String query = "select sd.product_id, sd.quantity, sd.date_t from\n" +
//            "                (select s.product_id, sum(quantity) as quantity , date_trunc('month',  sale_date) as date_t\n" +
//            "                      from sale s\n" +
//            "                      where product_id = ?\n" +
//            "                      group by date_t, s.product_id\n" +
//            "                      order by date_t) sd ;\n";

    private final Logger log = LoggerFactory.getLogger(SaleService.class);

    private final SaleRepository saleRepository;
    private final SaleTool saleTool;
    private final ProductService productService;
    private final SaleInMonthRepository saleInMonthRepository;

    public List<Sale> getAllByProductId(UUID productId) {
        return saleRepository.findAllByProductId(productId);
    }

    public Optional<Sale> getById(UUID uuid) {
        return saleRepository.findById(uuid);
    }

    // todo find out if i can return optional
    public Sale create(Sale sale) {
        Sale newSale = saleTool.create(
                sale.getId(),
                sale.getProductId(),
                sale.getQuantity(),
                sale.getCost(),
                sale.getDate());
        return saleRepository.save(newSale);
    }

    @Transactional
    public Optional<Sale> update(Sale sale) {
        return saleRepository.findById(sale.getId())
                .map(old -> saleTool.update(old, sale))
                .map(saleRepository::save);
    }

    public void delete(UUID id) {
        saleRepository.deleteById(id);
    }

    // todo it should be outside my core logic. Maybe in api.rest
    // todo make it like in ppudgy project with consumer and validation of parsed string
    // todo make save batch
    @Transactional
    public void handleFileWithSales(InputStream stream, Charset charset) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Sale sale = parseLine(line);
                if (!saleRepository.existsByProductIdAndDateAndCost(sale.getProductId(), sale.getDate(), sale.getCost())) {
                    saleRepository.save(sale);
                } else {
                    log.error("this row already exist: " + sale);
                }
            }
        } catch (IOException e) {
            log.error("IOException with sales file in saleService: %s%n", e);
        }

    }

    private Sale parseLine(String string) {
        // todo it's not save. I can't be sure that string contains required parts
        String[] splitRes = string.split(";");
        UUID productId = productService.getIdByNameOrSave(splitRes[0]);
        return saleTool.create(productId, splitRes[2], splitRes[3], splitRes[1]);
    }

    //// SaleInMonth

    public List<SaleInMonth> getAllSaleInMonthByProductId(UUID productId) {
        return saleInMonthRepository.findAllByProductId(productId);
    }

}
