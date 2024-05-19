package ru.aquamarina.saleadviser.controller.mappers;

import org.mapstruct.Mapper;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.controller.dto.SaleDTO;
import ru.aquamarina.saleadviser.model.Sale;

import java.util.List;

@Mapper(config = AppMapperConfig.class)
public interface SaleMapper {

    SaleDTO mapToDTO(Sale sale);

    List<SaleDTO> mapToDTO(List<Sale> saleList);

    Sale fromDTO(SaleDTO saleDTO);
    
    List<Sale> fromDTO(List<SaleDTO> saleDTO);
}
