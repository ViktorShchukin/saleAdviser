package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aquamarina.saleadviser.core.model.Group;
import ru.aquamarina.saleadviser.core.model.GroupAndProduct;
import ru.aquamarina.saleadviser.core.tools.GroupTool;
import ru.aquamarina.saleadviser.repository.GroupRepository;
import ru.aquamarina.saleadviser.repository.GroupAndProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupAndProductRepository groupsAndProductsRepository;
    private final GroupTool groupTool;
    private final ProductService productService;

    public GroupService(GroupRepository groupRepository,
                        GroupAndProductRepository groupsAndProductsRepository,
                        GroupTool groupTool,
                        ProductService productService) {
        this.groupRepository = groupRepository;
        this.groupsAndProductsRepository = groupsAndProductsRepository;
        this.groupTool = groupTool;
        this.productService = productService;
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
        groupsAndProductsRepository.deleteAllByGroupId(id);
        groupRepository.deleteById(
                id
        );
    }

    public List<GroupAndProduct> getAllGroupAndProduct(UUID id) {
        return groupsAndProductsRepository.findAllByGroupId(id);
    }

    public GroupAndProduct save(GroupAndProduct groupsAndProducts){
        assert groupRepository.existsById(groupsAndProducts.getGroupId());
        assert productService.existsById(groupsAndProducts.getProductId());
        return groupsAndProductsRepository.save(groupsAndProducts);
    }

    public Optional<GroupAndProduct> getGroupAndProduct(UUID id, UUID productId) {
        return groupsAndProductsRepository.findByGroupIdAndProductId(id, productId);
    }

    public Optional<GroupAndProduct> update(GroupAndProduct groupsAndProducts) {
        return groupsAndProductsRepository
                .findByGroupIdAndProductId(groupsAndProducts.getGroupId(), groupsAndProducts.getProductId())
                .map(old -> groupTool.update(old, groupsAndProducts))
                .map(groupsAndProductsRepository::save);
    }

    public void deleteGroupAndProduct(UUID id, UUID productId) {
        groupsAndProductsRepository.deleteByGroupIdAndProductId(id, productId);
    }
}
