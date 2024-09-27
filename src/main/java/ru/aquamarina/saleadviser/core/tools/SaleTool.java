package ru.aquamarina.saleadviser.core.tools;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.aquamarina.saleadviser.config.AppMapperConfig;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.core.model.SaleInterface;
import ru.aquamarina.saleadviser.core.model.TableFunction;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(config = AppMapperConfig.class)
public interface SaleTool {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Sale create(UUID id,
                UUID productId,
                int quantity,
                float cost,
                ZonedDateTime date);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Sale create(UUID productId,
                String quantity,
                String cost,
                String date);

    @Mapping(target = "id", ignore = true)
    Sale update(@MappingTarget Sale toUpdate, Sale fromUpdate);

    // todo it's work, but need to be tested more
    default ZonedDateTime saleStringToDate(String dateTime) throws Exception {
        ZonedDateTime parsed;
        ZoneId zone = ZoneId.of("UTC");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm:ss").withZone(zone);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'H:mm:ss").withZone(zone);
        try {
            parsed = ZonedDateTime.parse(dateTime, formatter1);
            return parsed;
        } catch (Exception ignored) {

        }
        try {
            parsed = ZonedDateTime.parse(dateTime, formatter2);
            return parsed;
        } catch (Exception e) {
            // todo change this to put it to log instead of throw exception
            throw new Exception("sale dateTime parsing error", e);
        }

    }

    // todo make shure that saleList is not nulll
    default TableFunction toTableFunction(List<? extends SaleInterface> saleList) {
        TableFunction function = new TableFunction();
        // todo is it normal?? about how i get product id???
        function.setProductId(saleList.get(0).getProductId());
        function.setListQuantity(toListQuantity(saleList));
        function.setListDate(toListDateTime(saleList));
        return function;
    }

    // todo write test for this.
    default List<ZonedDateTime> toListDateTime(List<? extends SaleInterface> saleList) {
        List<ZonedDateTime> dateList = new ArrayList<ZonedDateTime>();
        int i = 0;
        while (i < saleList.size()) {
            dateList.add(saleList.get(i).getDate());
            i++;
        }
        return dateList;
    }

    // todo write test for this
    default int[] toListQuantity(List<? extends SaleInterface> saleList) {
        int[] quantityList = new int[saleList.size()];
        int i = 0;
        while (i < saleList.size()) {
            quantityList[i] = saleList.get(i).getQuantity();
            i++;
        }
        return quantityList;
    }
}
