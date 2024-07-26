package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.model.GroupsAndProducts;
import ru.aquamarina.saleadviser.core.model.Sale;

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
    GroupsAndProducts createGroupsAndProductNoValue(UUID groupId, UUID productId);

    @Mapping(target = "groupId", ignore = true)
    GroupsAndProducts update(@MappingTarget GroupsAndProducts old, GroupsAndProducts groupsAndProducts);
}
