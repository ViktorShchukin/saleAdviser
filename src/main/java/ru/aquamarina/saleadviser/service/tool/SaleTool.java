package ru.aquamarina.saleadviser.service.tool;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.model.Sale;

import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(config = AppMapperConfig.class)
public interface SaleTool {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Sale create(UUID id,
                UUID productId,
                int quantity,
                float totalSum,
                ZonedDateTime saleDate);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Sale create(UUID productId,
                String quantity,
                String totalSum,
                String saleDate);

    @Mapping(target = "id", ignore = true)
    Sale updata(@MappingTarget Sale toUpdate, Sale fromUpdate);
}
