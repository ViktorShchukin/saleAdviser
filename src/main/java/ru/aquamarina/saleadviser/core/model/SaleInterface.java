package ru.aquamarina.saleadviser.core.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface SaleInterface {

    UUID getProductId();

    int getQuantity();

    ZonedDateTime getDate();
}
