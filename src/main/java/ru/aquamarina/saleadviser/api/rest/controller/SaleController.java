package ru.aquamarina.saleadviser.api.rest.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aquamarina.saleadviser.api.rest.dto.SaleDTO;
import ru.aquamarina.saleadviser.api.rest.mappers.SaleMapper;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.service.SaleService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

// todo at first implement interface then extend it and create logic. Leave annotation in interface
// todo handle exceptions with @ControllerAdvice and @ExceptionHandler
@RestController
@RequestMapping("/dictionary")
public class SaleController {

    // todo make it final
    private SaleMapper saleMapper;
    private SaleService saleService;

    public SaleController(SaleMapper saleMapper, SaleService saleService) {
        this.saleMapper = saleMapper;
        this.saleService = saleService;
    }

    // todo make it Pageable
    @GetMapping("/product/{productId}/sale")
    public ResponseEntity<List<SaleDTO>> getAllByProductId(@NonNull @PathVariable("productId") UUID productId) {
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

    @PostMapping("/product/{productId}/sale")
    public ResponseEntity<SaleDTO> create(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        assert productId.equals(saleDTO.getProductId());
        Sale saved = saleService.create(saleMapper.fromDTO(saleDTO));
        return ResponseEntity.ok(saleMapper.mapToDTO(saved));
    }

    @PutMapping("/product/{productId}/sale/{id}")
    public ResponseEntity<SaleDTO> update(@NonNull @PathVariable("productId") UUID productId,
                                          @NonNull @PathVariable("id") UUID id,
                                          @NonNull @RequestBody SaleDTO saleDTO) {
        assert productId.equals(saleDTO.getProductId());
        assert id.equals(saleDTO.getId());
        return saleService
                .update(saleMapper.fromDTO(saleDTO))
                .map(saleMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/product/{productId}/sale/{id}")
    public ResponseEntity<?> delete(@NonNull @PathVariable("productId") UUID productId,
                                    @NonNull @PathVariable("id") UUID id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // todo think about the validation of MultipartFile
    @PostMapping("/sale/file/upload")
    public ResponseEntity<?> uploadFileWithSale(@RequestParam("file") MultipartFile file) {
        try {
            saleService.handleFileWithSales(file.getInputStream());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // todo maybe send the message???
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }
}