package ru.aquamarina.saleadviser.service.tool;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.model.Sale;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    Sale update(@MappingTarget Sale toUpdate, Sale fromUpdate);

    // todo it's work, but need to be tested more
    default ZonedDateTime saleStringToDate(String dateTime) throws Exception {
        ZonedDateTime parsed;
        ZoneId zone = ZoneId.of("UTC");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm:ss").withZone(zone);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'H:mm:ss").withZone(zone);
        try{
            parsed = ZonedDateTime.parse(dateTime, formatter1);
            return parsed;
        } catch (Exception ignored){

        }
        try {
            parsed = ZonedDateTime.parse(dateTime, formatter2);
            return parsed;
        }catch (Exception e) {
            // todo change this to put it to log instead of throw exception
            throw new Exception("sale dateTime parsing error", e);
        }

    }
}
