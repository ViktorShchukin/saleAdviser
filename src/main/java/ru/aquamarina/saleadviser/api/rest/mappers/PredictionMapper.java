package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.PredictionDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Prediction;

@Mapper(config = AppMapperConfig.class)
public interface PredictionMapper {

    PredictionDTO toDto(Prediction prediction);
}
