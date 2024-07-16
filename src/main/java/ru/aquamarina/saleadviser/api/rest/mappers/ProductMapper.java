package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.ProductDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Product;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTO(List<Product> productList);

    Product fromDTO(ProductDTO productDTO);
}
