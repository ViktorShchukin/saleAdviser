package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Prediction;

import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(config = AppMapperConfig.class)
public interface PredictionTool {

    Prediction create(UUID productId,
                      float value,
                      ZonedDateTime predictionDate,
                      ZonedDateTime timestamp);
}
