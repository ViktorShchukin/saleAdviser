package ru.aquamarina.saleadviser.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.controller.dto.ProductDTO;
import ru.aquamarina.saleadviser.model.Product;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTO(List<Product> productList);

    Product fromDTO(ProductDTO productDTO);
}
