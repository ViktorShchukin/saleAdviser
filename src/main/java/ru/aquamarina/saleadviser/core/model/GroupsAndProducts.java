package ru.aquamarina.saleadviser.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@IdClass(GroupsAndProductsId.class)
public class GroupsAndProducts {

    @Id
    private UUID productId;

    @Id
    private UUID groupId;

    @Column(name = "custom_value")
    private int customValue;

    public GroupsAndProducts() {
    }

    public GroupsAndProducts(UUID productId, UUID groupId, int customValue) {
        this.productId = productId;
        this.groupId = groupId;
        this.customValue = customValue;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public int getCustomValue() {
        return customValue;
    }

    public void setCustomValue(int customValue) {
        this.customValue = customValue;
    }
}
