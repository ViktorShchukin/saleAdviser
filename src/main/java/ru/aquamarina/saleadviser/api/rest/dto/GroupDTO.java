package ru.aquamarina.saleadviser.api.rest.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class GroupDTO {

    UUID id;
    String name;
    ZonedDateTime creationDate;
}
