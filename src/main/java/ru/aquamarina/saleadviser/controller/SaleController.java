package ru.aquamarina.saleadviser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.aquamarina.saleadviser.controller.dto.SaleDTO;
import ru.aquamarina.saleadviser.controller.mappers.ProductMapper;
import ru.aquamarina.saleadviser.controller.mappers.SaleMapper;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.service.SaleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dictionary/product/{productId}/sale")
public class SaleController {

    private SaleMapper saleMapper;
    private SaleService saleService;

    public SaleController(SaleMapper saleMapper, SaleService saleService) {
        this.saleMapper = saleMapper;
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAll(@NonNull @PathVariable("productId") UUID productId){
        return ResponseEntity.ok(
                saleMapper.mapToDTO(
                        saleService.getAllByProductId(productId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getById(@NonNull @PathVariable("productId") UUID productId,
                                           @NonNull @PathVariable("id") UUID id) {
        return saleService
                .getById(id)
                .map(saleMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        Sale saved = saleService.create(saleMapper.fromDTO(saleDTO));
        return ResponseEntity.ok(saleMapper.mapToDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @PathVariable("id") UUID id,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        return saleService
                .update(saleMapper.fromDTO(saleDTO))
                .map(saleMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@NonNull @PathVariable("productId") UUID productId,
                                    @NonNull @PathVariable("id") UUID id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}