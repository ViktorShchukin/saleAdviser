package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.Sale;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    List<Sale> findAllByProductId(UUID productId);

    boolean existsByProductIdAndDateAndCost(UUID productId, ZonedDateTime saleDate, float Cost);

}
