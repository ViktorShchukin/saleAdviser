package ru.aquamarina.saleadviser.core.model;

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
public class Prediction {

    private UUID productId;
    private float value;
    private ZonedDateTime predictionDate; // the target date of prediction
    private ZonedDateTime timestamp; // when prediction was made
}
