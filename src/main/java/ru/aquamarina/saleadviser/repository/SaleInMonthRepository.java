package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.SaleInMonth;

import java.util.List;
import java.util.UUID;

public interface SaleInMonthRepository extends JpaRepository<SaleInMonth, Long> {


    List<SaleInMonth> findAllByProductId(UUID productId);
}
