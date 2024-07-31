package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;
import ru.aquamarina.saleadviser.core.model.Product;

import java.util.UUID;

@Data
public class GroupRowDTO {

    UUID groupId;
    Product product;
    int customValue;

}
