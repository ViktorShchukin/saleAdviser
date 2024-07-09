package ru.aquamarina.saleadviser.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.controller.dto.PredictionDTO;
import ru.aquamarina.saleadviser.model.Prediction;

@Mapper(config = AppMapperConfig.class)
public interface PredictionMapper {

    PredictionDTO toDto(Prediction prediction);
}
