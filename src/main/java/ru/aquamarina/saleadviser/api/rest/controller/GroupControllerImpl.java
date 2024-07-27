package ru.aquamarina.saleadviser.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.aquamarina.saleadviser.api.rest.dto.GroupAndProductDTO;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.api.rest.mappers.GroupMapper;
import ru.aquamarina.saleadviser.api.rest.mappers.ProductMapper;
import ru.aquamarina.saleadviser.core.model.GroupAndProduct;
import ru.aquamarina.saleadviser.service.GroupService;

import java.util.List;
import java.util.UUID;

@RestController
public class GroupControllerImpl implements GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final ProductMapper productMapper;

    public GroupControllerImpl(GroupService groupService, GroupMapper groupMapper, ProductMapper productMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.productMapper = productMapper;
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
    public ResponseEntity<List<GroupAndProductDTO>> getAllGroupAndProduct(UUID id) {
        List<GroupAndProduct> result = groupService.getAllGroupAndProduct(id);
        return ResponseEntity.ok(groupMapper.toDtoGroupRow(result));
    }

    @Override
    public ResponseEntity<GroupAndProductDTO> addGroupAndProduct(UUID id, GroupAndProductDTO groupRowDTO) {
        assert id.equals(groupRowDTO.getGroupId());
        assert false;
        var result = groupService.save(groupMapper.fromDto(groupRowDTO));
        return ResponseEntity.ok(groupMapper.toDto(result));
    }

    @Override
    public ResponseEntity<GroupAndProductDTO> getGroupAndProduct(UUID id, UUID productId) {
        return groupService
                .getGroupAndProduct(id, productId)
                .map(groupMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public ResponseEntity<GroupAndProductDTO> updateGroupAndProduct(UUID id, UUID productId, GroupAndProductDTO groupRowDTO) {
        assert id.equals(groupRowDTO.getGroupId());
        assert productId.equals(groupRowDTO.getProductId());
        return groupService
                .update(groupMapper.fromDto(groupRowDTO))
                .map(groupMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public ResponseEntity<?> deleteGroupAndProduct(UUID id, UUID productId) {
        groupService.deleteGroupAndProduct(id, productId);
        return ResponseEntity.noContent().build();
    }
}
