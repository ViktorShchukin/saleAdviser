package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class PredictionDTO {

    // todo can use @JsonProperty and @JsonSerialize
    // todo use javax.validation
    private UUID productId;
    private double value;
    private ZonedDateTime timestamp; // when prediction was made
    private ZonedDateTime predictionDate; // the target date of prediction
}
