package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.GroupsAndProducts;
import ru.aquamarina.saleadviser.core.model.GroupsAndProductsId;

public interface GroupsAndProductsRepository extends JpaRepository<GroupsAndProducts, GroupsAndProductsId> {
}
