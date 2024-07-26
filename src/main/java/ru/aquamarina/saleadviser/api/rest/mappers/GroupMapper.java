package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.api.rest.dto.GroupRowDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.model.GroupsAndProducts;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface GroupMapper {

    GroupDTO toDto(Group group);

    Group fromDto(GroupDTO groupDTO);

    List<GroupDTO> toDto(List<Group> groupList);

    GroupRowDTO toDto(GroupsAndProducts groupsAndProducts);

    GroupsAndProducts fromDto(GroupRowDTO groupRowDTO);

    List<GroupRowDTO> toDtoGroupRow(List<GroupsAndProducts> groupsAndProducts);
}
