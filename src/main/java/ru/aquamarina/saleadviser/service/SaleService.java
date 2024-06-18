package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.repository.SaleRepository;
import ru.aquamarina.saleadviser.service.tool.SaleTool;

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

    // todo end this
    // todo should i close stream
    // todo accept ImputStream and then BufferedReader(ImputStream) and then process
    public void handleFileWithSales(InputStream stream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Sale sale = parseLine(line);
                saleRepository.save(sale);
            }
        } catch (IOException e) {
            System.err.format("IOException with sales file in saleService: %s%n", e);
        }

    }


    // no need to this. but I can't delete it
    private String readLine(Reader reader) throws IOException {
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        while ((ch = reader.read()) != '\n') {
            stringBuilder.append(ch);
        }
        if (!stringBuilder.isEmpty()) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private Sale parseLine(String string) {
        // todo it's not save. I can't be sure that string contains required parts
        String[] splitRes = string.split(";");
        UUID productId = productService.getIdByNameOrSave(splitRes[0]);
        return saleTool.create(productId, splitRes[2], splitRes[3], splitRes[1]);
    }
}
