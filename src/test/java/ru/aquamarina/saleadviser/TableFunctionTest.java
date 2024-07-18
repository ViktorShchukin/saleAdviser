package ru.aquamarina.saleadviser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aquamarina.saleadviser.core.model.TableFunction;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TableFunctionTest {

    // todo what precision i should use??? read about it
    private static final float EPS = 0.1F;

    @ParameterizedTest
    @MethodSource("testGetValueProducer")
    void testGetValue(ZonedDateTime input, float expected) {
        TableFunction tableFunction = prepareTableFunction();
        float actual = tableFunction.getValue(input);
        assertTrue((expected - actual) < EPS);
    }

    /**
     * stream of data samples TableFunction from this.prepareTableFunction().
     *
     * @return stream of Argument.of(input for method, expected returned value)
     */
    private static Stream<Arguments> testGetValueProducer() {
        return Stream.of(
                Arguments.of("2000-01-01T00:00:00Z", 0),
                Arguments.of("2005-01-01T00:00:00Z", 5),
                Arguments.of("2009-01-01T00:00:00Z", 9),
                Arguments.of("1000-01-01T00:00:00Z", 0),
                Arguments.of("3000-01-01T00:00:00Z", 9),
                Arguments.of(ZonedDateTime.parse("2005-01-01T00:00:00Z").plusMonths(5), 5.5F)
        );
    }

    private static TableFunction prepareTableFunction() {
        List<ZonedDateTime> dateTimeList = new ArrayList<>();
        dateTimeList.add(ZonedDateTime.parse("2000-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2001-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2002-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2003-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2004-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2005-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2006-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2007-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2008-01-01T00:00:00Z"));
        dateTimeList.add(ZonedDateTime.parse("2009-01-01T00:00:00Z"));
        return new TableFunction(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                dateTimeList);
    }
}
