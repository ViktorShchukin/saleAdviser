package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Product;

@Mapper(config = AppMapperConfig.class)
public interface ProductTool {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "name", source = "name")
    Product create(String name);

    @Mapping(target = "id", ignore = true)
    Product update(@MappingTarget Product toUpdate, Product fromUpdate);
}
