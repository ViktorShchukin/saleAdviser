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

    UUID productId;
    float value;
    ZonedDateTime predictionDate; // the target date of prediction
    ZonedDateTime timestamp; // when prediction was made
}