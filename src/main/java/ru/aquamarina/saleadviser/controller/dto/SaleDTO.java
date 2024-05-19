package ru.aquamarina.saleadviser.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class SaleDTO {

    private UUID id;

    private UUID productId;

    private int quantity;

    private float totalSum;

    private ZonedDateTime saleDate;
}
