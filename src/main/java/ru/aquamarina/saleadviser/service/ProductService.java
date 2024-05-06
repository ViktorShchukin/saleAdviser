package ru.aquamarina.saleadviser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.model.Product;
import ru.aquamarina.saleadviser.repository.ProductRepository;
import ru.aquamarina.saleadviser.service.tool.ProductTool;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTool productTool;
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(UUID id) {
        return productRepository.findById(id);
    }

    public Product save(String name){
        Product newProduct = productTool.create(name);
        return productRepository.save(newProduct);
    }

    public Optional<Product> update(UUID id, Product fromUpdate){
        return productRepository.findById(id)
                .map(old -> productTool.update(old, fromUpdate))
                .map(productRepository::save);
    }

    public void delete(UUID id){
        productRepository.deleteById(id);
    }
}
