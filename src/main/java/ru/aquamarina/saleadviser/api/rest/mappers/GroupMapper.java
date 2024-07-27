package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.GroupAndProductDTO;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.model.GroupAndProduct;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface GroupMapper {

    GroupDTO toDto(Group group);

    Group fromDto(GroupDTO groupDTO);

    List<GroupDTO> toDto(List<Group> groupList);

    GroupAndProductDTO toDto(GroupAndProduct groupsAndProducts);

    GroupAndProduct fromDto(GroupAndProductDTO groupRowDTO);

    List<GroupAndProductDTO> toDtoGroupRow(List<GroupAndProduct> groupsAndProducts);
}
