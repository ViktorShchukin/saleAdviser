package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aquamarina.saleadviser.controller.dto.SaleDTO;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.repository.SaleRepository;
import ru.aquamarina.saleadviser.service.tool.SaleTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleTool saleTool;

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
                .map(old -> saleTool.updata(old, sale))
                .map(saleRepository::save);
    }

    public void delete(UUID id) {
        saleRepository.deleteById(id);
    }

    // todo ad this
    // todo should i close stream
    public void handleFileWithSales(MultipartFile file) throws IOException {
        File targetFile = File.createTempFile("tmp", ".csv");
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(
                    inputStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
//        Charset charset =
        try (BufferedReader reader = Files.newBufferedReader(targetFile.toPath())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // todo doing operation on line
            }
        } catch (IOException e) {
            System.err.format("IOException with sales file in saleService: %s%n", e);
        }
        targetFile.delete();
    }


}
