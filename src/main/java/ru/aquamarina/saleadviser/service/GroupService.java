package ru.aquamarina.saleadviser.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.tools.GroupTool;
import ru.aquamarina.saleadviser.repository.GroupRepository;
import ru.aquamarina.saleadviser.repository.GroupsAndProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupsAndProductsRepository groupsAndProductsRepository;
    private final GroupTool groupTool;

    public GroupService(GroupRepository groupRepository,
                        GroupsAndProductsRepository groupsAndProductsRepository,
                        GroupTool groupTool) {
        this.groupRepository = groupRepository;
        this.groupsAndProductsRepository = groupsAndProductsRepository;
        this.groupTool = groupTool;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> getById(UUID id) {
        return groupRepository.findById(id);
    }

    public Group save(Group group) {
        Group newGroup = groupTool.create(group.getName());
        return groupRepository.save(newGroup);
    }

    public Optional<Group> update(Group group) {
        return groupRepository
                .findById(group.getId())
                .map(old -> groupTool.update(old, group))
                .map(groupRepository::save);
    }

    public void delete(UUID id) {
        groupRepository.deleteById(
                id
        );
    }
}
