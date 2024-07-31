package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.model.GroupAndProduct;
import ru.aquamarina.saleadviser.core.model.GroupRow;
import ru.aquamarina.saleadviser.core.model.Product;

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
}
