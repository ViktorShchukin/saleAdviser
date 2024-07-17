package ru.aquamarina.saleadviser.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.aquamarina.saleadviser.api.rest.dto.PredictionDTO;
import ru.aquamarina.saleadviser.api.rest.dto.ProductDTO;
import ru.aquamarina.saleadviser.api.rest.mappers.PredictionMapper;
import ru.aquamarina.saleadviser.api.rest.mappers.ProductMapper;
import ru.aquamarina.saleadviser.core.model.Prediction;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.service.PredictionService;
import ru.aquamarina.saleadviser.service.ProductService;
import ru.aquamarina.saleadviser.service.SaleService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

//@AllArgsConstructor
@RestController
@RequestMapping("/dictionary/product")
public class ProductController {

    private final ProductService productService;
    private final PredictionService predictionService;
    private final SaleService saleService;
    private final ProductMapper productMapper;
    private final PredictionMapper predictionMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             PredictionService predictionService,
                             SaleService saleService, PredictionMapper predictionMapper) {
        this.productService = productService;
        this.predictionService = predictionService;
        this.productMapper = productMapper;
        this.saleService = saleService;
        this.predictionMapper = predictionMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        var result = productService.getAll();
        return ResponseEntity.ok(productMapper.toDTO(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@NonNull @PathVariable("id") UUID id) {
        return productService.getById(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@NonNull @RequestBody ProductDTO body) {
        var res = productService.save(body.getName());
        return ResponseEntity.ok(productMapper.toDTO(res));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@NonNull @PathVariable("id") UUID id,
                                             @NonNull @RequestBody ProductDTO fromUpdate) {
        return productService
                .update(id, productMapper.fromDTO(fromUpdate))
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@NonNull @PathVariable("id") UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/prediction/{date}")
    public ResponseEntity<PredictionDTO> getPrediction(@NonNull @PathVariable("id") UUID id,
                                                       @NonNull @PathVariable("date") ZonedDateTime targetDate) {
        List<Sale> saleList = saleService.getAllByProductId(id);
        Prediction prediction = predictionService.get(saleList, targetDate);
        return ResponseEntity.ok(predictionMapper.toDto(prediction));
    }
}