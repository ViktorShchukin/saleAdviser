package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.model.Sale;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
