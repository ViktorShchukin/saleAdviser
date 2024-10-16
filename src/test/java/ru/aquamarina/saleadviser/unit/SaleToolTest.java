package ru.aquamarina.saleadviser.unit;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SaleToolTest {

    SaleTool mapper = Mappers.getMapper(SaleTool.class);


    @Test
    void testToListDateTime() {
        List<Sale> saleList = prepareSaleList();
        List<ZonedDateTime> dateTimeListFromMapper = mapper.toListDateTime(saleList);
        assertThat(dateTimeListFromMapper)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(2);
        assertThat(dateTimeListFromMapper.getFirst())
                .isEqualTo(ZonedDateTime.parse("2024-01-01T00:00:00Z"));
    }

    @Test
    void testToListQuantity() {
        List<Sale> saleList = prepareSaleList();
        int[] quantityListFromMapper = mapper.toListQuantity(saleList);
        assertThat(quantityListFromMapper)
                .isNotNull()
                .isNotEmpty();
        assertThat(quantityListFromMapper.length).isEqualTo(2);
        AssertionsForClassTypes.assertThat(quantityListFromMapper[0])
                .isEqualTo(1);
    }

    /**
     * help method to form sale list
     */
    private List<Sale> prepareSaleList() {
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        ZonedDateTime dateTime1 = ZonedDateTime.parse("2024-01-01T00:00:00Z");
        ZonedDateTime dateTime2 = ZonedDateTime.parse("2025-01-01T00:00:00Z");
        Sale sale1 = new Sale(
                uuid,
                uuid,
                1,
                1,
                dateTime1
        );
        Sale sale2 = new Sale(
                uuid,
                uuid,
                1,
                1,
                dateTime2
        );
        List<Sale> saleList = new ArrayList<>();
        saleList.add(sale1);
        saleList.add(sale2);
        return saleList;
    }

}
