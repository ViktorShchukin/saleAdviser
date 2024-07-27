package ru.aquamarina.saleadviser.api.rest.dto;

import java.util.UUID;

public class GroupAndProductDTO {

    private UUID productId;
    private UUID groupId;
    private int customValue;

    public GroupAndProductDTO() {
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
