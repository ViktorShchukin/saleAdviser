package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.GroupAndProduct;
import ru.aquamarina.saleadviser.core.model.GroupAndProductId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupAndProductRepository extends JpaRepository<GroupAndProduct, GroupAndProductId> {
    List<GroupAndProduct> findAllByGroupId(UUID id);

    void deleteByGroupIdAndProductId(UUID id, UUID productId);

    void deleteAllByGroupId(UUID id);

    Optional<GroupAndProduct> findByGroupIdAndProductId(UUID id, UUID productId);
}
