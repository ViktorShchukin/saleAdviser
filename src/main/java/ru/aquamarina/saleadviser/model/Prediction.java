package ru.aquamarina.saleadviser.model;

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
    ZonedDateTime timestamp; // when prediction was made
    ZonedDateTime predictionDate; // the target date of prediction
}
