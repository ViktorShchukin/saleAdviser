package ru.aquamarina.saleadviser;

import org.junit.jupiter.api.Test;
import ru.aquamarina.saleadviser.core.calculator.Calculator;
import ru.aquamarina.saleadviser.core.calculator.HeuristicCalculator;
import ru.aquamarina.saleadviser.core.model.TableFunction;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HeuristicCalculatorTest {

    private static final double EPS = 0.1F;

    @Test
    void testPredict() {
        Calculator calculator = new HeuristicCalculator(
                prepareTableFunction(),
                6,
                ZonedDateTime.parse("2001-08-01T00:00:00Z")
        );
        double expected = 2;
        double actual = calculator.predict(ZonedDateTime.parse("2001-09-01T00:00:00Z")).getValue();
        assertTrue(Math.abs(expected - actual) < EPS);

    }

    private static TableFunction prepareTableFunction() {
        List<ZonedDateTime> dateTimeList = List.of(
                ZonedDateTime.parse("2000-01-01T00:00:00Z"),
                ZonedDateTime.parse("2000-02-01T00:00:00Z"),
                ZonedDateTime.parse("2000-03-01T00:00:00Z"),
                ZonedDateTime.parse("2000-04-01T00:00:00Z"),
                ZonedDateTime.parse("2000-05-01T00:00:00Z"),
                ZonedDateTime.parse("2000-06-01T00:00:00Z"),
                ZonedDateTime.parse("2000-07-01T00:00:00Z"),
                ZonedDateTime.parse("2000-08-01T00:00:00Z"),
                ZonedDateTime.parse("2000-09-01T00:00:00Z"),
                ZonedDateTime.parse("2000-10-01T00:00:00Z"),
                ZonedDateTime.parse("2000-11-01T00:00:00Z"),
                ZonedDateTime.parse("2000-12-01T00:00:00Z"),
                ZonedDateTime.parse("2001-01-01T00:00:00Z"),
                ZonedDateTime.parse("2001-02-01T00:00:00Z"),
                ZonedDateTime.parse("2001-03-01T00:00:00Z"),
                ZonedDateTime.parse("2001-04-01T00:00:00Z"),
                ZonedDateTime.parse("2001-05-01T00:00:00Z"),
                ZonedDateTime.parse("2001-06-01T00:00:00Z"),
                ZonedDateTime.parse("2001-07-01T00:00:00Z"),
                ZonedDateTime.parse("2001-08-01T00:00:00Z"),
                ZonedDateTime.parse("2001-09-01T00:00:00Z"),
                ZonedDateTime.parse("2001-10-01T00:00:00Z"),
                ZonedDateTime.parse("2001-11-01T00:00:00Z"),
                ZonedDateTime.parse("2001-12-01T00:00:00Z")
        );
        return new TableFunction(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, },
                dateTimeList);
    }
}
