package ru.aquamarina.saleadviser.controller.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PredictionDTO {

    UUID productId;
    float value;
    ZonedDateTime timestamp; // when prediction was made
    ZonedDateTime predictionDate; // the target date of prediction
}
