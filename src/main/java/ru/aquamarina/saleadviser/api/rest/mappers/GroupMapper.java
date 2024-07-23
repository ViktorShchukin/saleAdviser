package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Group;

@Mapper(config = AppMapperConfig.class)
public interface GroupMapper {

    GroupDTO toDto(Group group);

    Group fromDto(GroupDTO groupDTO);
}
