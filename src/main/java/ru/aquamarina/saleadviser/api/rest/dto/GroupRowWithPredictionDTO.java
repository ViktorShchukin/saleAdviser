package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;
import ru.aquamarina.saleadviser.core.model.Prediction;
import ru.aquamarina.saleadviser.core.model.Product;

import java.util.UUID;

@Data
public class GroupRowWithPredictionDTO {

    UUID groupId;
    Product product;
    int customValue;
    Prediction prediction;
}
