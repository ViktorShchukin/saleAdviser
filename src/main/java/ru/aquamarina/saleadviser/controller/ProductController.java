package ru.aquamarina.saleadviser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.aquamarina.saleadviser.controller.dto.ProductDTO;
import ru.aquamarina.saleadviser.controller.mappers.ProductMapper;
import ru.aquamarina.saleadviser.service.ProductService;

import java.util.List;
import java.util.UUID;

//@AllArgsConstructor
@RestController
@RequestMapping("/dictionary/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        var result =productService.getAll();
        return ResponseEntity.ok(productMapper.toDTO(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@NonNull @PathVariable("id")  UUID id){
        return productService.getById(id)
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@NonNull @RequestBody ProductDTO body){
        var res = productService.save(body.getName());
        return ResponseEntity.ok(productMapper.toDTO(res));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@NonNull @PathVariable("id") UUID id,
                                             @NonNull @RequestBody ProductDTO fromUpdate){
        return productService
                .update(id, productMapper.fromDTO(fromUpdate))
                .map(productMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@NonNull @PathVariable("id") UUID id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}