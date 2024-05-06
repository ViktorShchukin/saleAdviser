package ru.aquamarina.saleadviser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
@Entity
public class Sale {

    @Id
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_sum")
    private float totalSum;

    @Column(name = "sale_date")
    private ZonedDateTime saleDate;
}
