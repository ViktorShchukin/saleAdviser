package ru.aquamarina.saleadviser.api.rest.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.api.rest.dto.ProductDTO;
import ru.aquamarina.saleadviser.api.rest.mappers.GroupMapper;
import ru.aquamarina.saleadviser.service.GroupService;

import java.util.List;
import java.util.UUID;

@RestController
public class GroupControllerImpl implements GroupController {

    GroupService groupService;
    GroupMapper groupMapper;

    public GroupControllerImpl(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @Override
    public ResponseEntity<List<GroupDTO>> getAll() {
        var result = groupService.getAll();
        return ResponseEntity.ok(groupMapper.toDto(result));
    }

    @Override
    public ResponseEntity<GroupDTO> getById(UUID id) {
        return groupService
                .getById(id)
                .map(groupMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public ResponseEntity<GroupDTO> save(GroupDTO groupDTO) {
        var result = groupService.save(groupMapper.fromDto(groupDTO));
        return ResponseEntity.ok(groupMapper.toDto(result));
    }

    @Override
    public ResponseEntity<GroupDTO> update(UUID id, GroupDTO groupDTO) {
        assert id.equals(groupDTO.getId());
        return groupService
                .update(groupMapper.fromDto(groupDTO))
                .map(groupMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProduct(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductDTO> saveToGroup(UUID id, UUID productId, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteFromGroup(UUID id, UUID productId) {
        return null;
    }
}
