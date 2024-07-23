package ru.aquamarina.saleadviser.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * representation group of Products
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_groups")
@Entity
public class Group {

    @Id
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "creation_date")
    ZonedDateTime creationDate;

}
