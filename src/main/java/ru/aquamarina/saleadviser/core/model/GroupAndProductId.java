package ru.aquamarina.saleadviser.core.model;

import java.io.Serializable;
import java.util.UUID;

public class GroupAndProductId implements Serializable {
    private UUID productId;
    private UUID groupId;

    public GroupAndProductId() {
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
}
