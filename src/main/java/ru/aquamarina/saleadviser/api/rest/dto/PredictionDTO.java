package ru.aquamarina.saleadviser.api.rest.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PredictionDTO {

    // todo can use @JsonProperty and @JsonSerialize
    // todo use javax.validation
    UUID productId;
    float value;
    ZonedDateTime timestamp; // when prediction was made
    ZonedDateTime predictionDate; // the target date of prediction
}