package ru.aquamarina.saleadviser.core.model;

import java.util.UUID;

public record GroupRowWithPrediction(
        UUID groupId,
        Product product,
        int customValue,
        Prediction prediction
) {
}
