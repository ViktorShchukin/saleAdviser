package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
