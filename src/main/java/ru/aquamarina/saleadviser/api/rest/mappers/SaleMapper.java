package ru.aquamarina.saleadviser.api.rest.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.api.rest.dto.SaleDTO;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Sale;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface SaleMapper {

    SaleDTO mapToDTO(Sale sale);

    List<SaleDTO> mapToDTO(List<Sale> saleList);

    Sale fromDTO(SaleDTO saleDTO);
    
    List<Sale> fromDTO(List<SaleDTO> saleDTO);
}
