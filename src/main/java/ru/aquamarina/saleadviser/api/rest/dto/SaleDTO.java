package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class SaleDTO {

    private UUID id;

    private UUID productId;

    private int quantity;

    private float cost;

    private ZonedDateTime saleDate;
}
