package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.repository.SaleRepository;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleTool saleTool;
    private final ProductService productService;

    public List<Sale> getAllByProductId(UUID productId) {
        return saleRepository.findAllByProductId(productId);
    }

    public Optional<Sale> getById(UUID uuid) {
        return saleRepository.findById(uuid);
    }

    // todo make all transactional
    // todo find out if i can return optional
    public Sale create(Sale sale) {
        Sale newSale = saleTool.create(
                sale.getId(),
                sale.getProductId(),
                sale.getQuantity(),
                sale.getTotalSum(),
                sale.getSaleDate());
        return saleRepository.save(newSale);
    }

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
    public void handleFileWithSales(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Sale sale = parseLine(line);
                saleRepository.save(sale);
            }
        } catch (IOException e) {
            // todo log this instead of system.err
            System.err.format("IOException with sales file in saleService: %s%n", e);
        }

    }

    private Sale parseLine(String string) {
        // todo it's not save. I can't be sure that string contains required parts
        String[] splitRes = string.split(";");
        UUID productId = productService.getIdByNameOrSave(splitRes[0]);
        return saleTool.create(productId, splitRes[2], splitRes[3], splitRes[1]);
    }
}
