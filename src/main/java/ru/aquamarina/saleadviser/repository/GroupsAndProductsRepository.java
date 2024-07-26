package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.GroupsAndProducts;
import ru.aquamarina.saleadviser.core.model.GroupsAndProductsId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupsAndProductsRepository extends JpaRepository<GroupsAndProducts, GroupsAndProductsId> {
    List<GroupsAndProducts> findAllByGroupId(UUID id);

    void deleteByGroupIdAndProductId(UUID id, UUID productId);

    void deleteAllByGroupId(UUID id);

    Optional<GroupsAndProducts> findByGroupIdAndProductId(UUID id, UUID productId);
}
