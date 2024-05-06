package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
