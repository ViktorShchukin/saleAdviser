package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.*;

import java.util.UUID;

@Mapper(config = AppMapperConfig.class)
public interface GroupTool {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "creationDate", expression = "java(java.time.ZonedDateTime.now())")
    Group create(String name);

    @Mapping(target = "id", ignore = true)
    Group update(@MappingTarget Group toUpdate, Group fromUpdate);

    @Mapping(target = "customValue", constant = "0")
    GroupAndProduct createGroupsAndProductNoValue(UUID groupId, UUID productId);

    @Mapping(target = "groupId", ignore = true)
    GroupAndProduct update(@MappingTarget GroupAndProduct old, GroupAndProduct groupsAndProducts);

    GroupRow createGroupRow(GroupAndProduct groupAndProduct, Product product);

    GroupRowWithPrediction createGroupRow(GroupRow groupRow, Prediction prediction);

    default String groupRowToCSVString(GroupRowWithPrediction groupRowWithPrediction) {
        return String.join(";",
                groupRowWithPrediction.product().getName(),
                String.valueOf(groupRowWithPrediction.prediction().getValue()),
                String.valueOf(groupRowWithPrediction.customValue()));
    }
}
