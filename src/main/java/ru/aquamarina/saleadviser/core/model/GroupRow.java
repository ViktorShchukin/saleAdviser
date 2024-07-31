package ru.aquamarina.saleadviser.core.model;

import java.util.UUID;

public record GroupRow(
        UUID groupId,
        Product product,
        int customValue
) {
}
