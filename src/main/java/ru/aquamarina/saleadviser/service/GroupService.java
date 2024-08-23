package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aquamarina.saleadviser.core.model.*;
import ru.aquamarina.saleadviser.core.tools.GroupTool;
import ru.aquamarina.saleadviser.repository.GroupRepository;
import ru.aquamarina.saleadviser.repository.GroupAndProductRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    private final String FILE_SUFFIX_CSV = ".csv";
    private final GroupRepository groupRepository;
    private final GroupAndProductRepository groupsAndProductsRepository;
    private final GroupTool groupTool;
    private final ProductService productService;
    private final PredictionService predictionService;

    public GroupService(GroupRepository groupRepository,
                        GroupAndProductRepository groupsAndProductsRepository,
                        GroupTool groupTool,
                        ProductService productService, PredictionService predictionService) {
        this.groupRepository = groupRepository;
        this.groupsAndProductsRepository = groupsAndProductsRepository;
        this.groupTool = groupTool;
        this.productService = productService;
        this.predictionService = predictionService;
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

    @Transactional
    public Optional<Group> update(Group group) {
        return groupRepository
                .findById(group.getId())
                .map(old -> groupTool.update(old, group))
                .map(groupRepository::save);
    }

    @Transactional
    public void delete(UUID id) {
        groupsAndProductsRepository.deleteAllByGroupId(id);
        groupRepository.deleteById(
                id
        );
    }

    public List<GroupAndProduct> getAllGroupAndProduct(UUID id) {
        return groupsAndProductsRepository.findAllByGroupId(id);
    }

    @Transactional
    public GroupAndProduct save(GroupAndProduct groupsAndProducts) {
        assert groupRepository.existsById(groupsAndProducts.getGroupId());
        assert productService.existsById(groupsAndProducts.getProductId());
        return groupsAndProductsRepository.save(groupsAndProducts);
    }

    public Optional<GroupAndProduct> getGroupAndProduct(UUID id, UUID productId) {
        return groupsAndProductsRepository.findByGroupIdAndProductId(id, productId);
    }

    @Transactional
    public Optional<GroupAndProduct> update(GroupAndProduct groupsAndProducts) {
        return groupsAndProductsRepository
                .findByGroupIdAndProductId(groupsAndProducts.getGroupId(), groupsAndProducts.getProductId())
                .map(old -> groupTool.update(old, groupsAndProducts))
                .map(groupsAndProductsRepository::save);
    }

    public void deleteGroupAndProduct(UUID id, UUID productId) {
        groupsAndProductsRepository.deleteByGroupIdAndProductId(id, productId);
    }

    @Transactional
    public Optional<GroupRow> getGroupRow(UUID id, UUID productId) {
        return groupsAndProductsRepository
                .findByGroupIdAndProductId(id, productId)
                .flatMap(groupAndProduct -> productService
                        .getById(productId)
                        .map(product -> groupTool.createGroupRow(groupAndProduct, product)));

    }

    @Transactional
    public List<GroupRow> getAllGroupRow(UUID id) {
        List<GroupAndProduct> groupAndProductList = groupsAndProductsRepository.findAllByGroupId(id);
        List<GroupRow> groupRowList = new ArrayList<>();
        for (GroupAndProduct row : groupAndProductList) {
            productService
                    .getById(row.getProductId())
                    .map(product -> groupTool.createGroupRow(row, product))
                    .map(groupRowList::add);
        }
        return groupRowList;
    }

    @Transactional
    public File getPredictionFile(UUID uuid, ZonedDateTime dateTime) {
        List<GroupRow> groupRowList = getAllGroupRow(uuid);
        List<GroupRowWithPrediction> groupRowWithPredictionList = groupRowList
                .stream()
                .map(groupRow -> {
                    Prediction res = predictionService.get(groupRow.product().getId(), dateTime);
                    return groupTool.createGroupRow(groupRow, res);
                })
                .toList();
        try {
            String file_prefix = ZonedDateTime.now().toString() + "-predictions-";
            File tmpFile = File.createTempFile(file_prefix, FILE_SUFFIX_CSV);
            try (BufferedWriter wr = Files.newBufferedWriter(tmpFile.toPath(), StandardOpenOption.WRITE)) {
                wr.write("name;prediction;custom value");
                for (GroupRowWithPrediction groupRowWithPrediction : groupRowWithPredictionList) {
                    wr.newLine();
                    String s = groupTool.groupRowToCSVString(groupRowWithPrediction);
                    wr.write(s);
                }
            }
            return tmpFile;
        } catch (IOException e) {
            // todo handle this in other way, i am not really excited about exception. used it only because have no good thought option for now
            throw new RuntimeException(this.getClass().toString() + " IO in getPredictionFile method");
        }
    }
}
