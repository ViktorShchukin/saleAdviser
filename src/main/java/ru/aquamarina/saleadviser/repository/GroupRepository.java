package ru.aquamarina.saleadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aquamarina.saleadviser.core.model.Group;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
