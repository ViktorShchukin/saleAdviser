package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.controller.dto.SaleDTO;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.repository.SaleRepository;
import ru.aquamarina.saleadviser.service.tool.SaleTool;

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
}
