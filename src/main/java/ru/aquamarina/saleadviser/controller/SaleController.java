package ru.aquamarina.saleadviser.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aquamarina.saleadviser.controller.dto.SaleDTO;
import ru.aquamarina.saleadviser.controller.mappers.SaleMapper;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.service.SaleService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dictionary")
public class SaleController {

    private SaleMapper saleMapper;
    private SaleService saleService;

    public SaleController(SaleMapper saleMapper, SaleService saleService) {
        this.saleMapper = saleMapper;
        this.saleService = saleService;
    }

    @GetMapping("/product/{productId}/sale")
    public ResponseEntity<List<SaleDTO>> getAllByProductId(@NonNull @PathVariable("productId") UUID productId){
        return ResponseEntity.ok(
                saleMapper.mapToDTO(
                        saleService.getAllByProductId(productId)));
    }

    @GetMapping("/product/{productId}/sale/{id}")
    public ResponseEntity<SaleDTO> getById(@NonNull @PathVariable("productId") UUID productId,
                                           @NonNull @PathVariable("id") UUID id) {
        return saleService
                .getById(id)
                .map(saleMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // todo check if product id from path and body are the same
    // todo for all methods and for product too
    @PostMapping("/product/{productId}/sale")
    public ResponseEntity<SaleDTO> create(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        Sale saved = saleService.create(saleMapper.fromDTO(saleDTO));
        return ResponseEntity.ok(saleMapper.mapToDTO(saved));
    }

    @PutMapping("/product/{productId}/sale/{id}")
    public ResponseEntity<SaleDTO> update(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @PathVariable("id") UUID id,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        return saleService
                .update(saleMapper.fromDTO(saleDTO))
                .map(saleMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    // todo should i check does this entity exist???
    @DeleteMapping("/product/{productId}/sale/{id}")
    public ResponseEntity<?> delete(@NonNull @PathVariable("productId") UUID productId,
                                    @NonNull @PathVariable("id") UUID id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sale/file/upload")
    public ResponseEntity<?> uploadFileWithSale(@RequestParam("file") MultipartFile file) {
        try {
            saleService.handleFileWithSales(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // todo maybe send the message???
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }
}