package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    UUID id;
    String name;
}
