package ru.aquamarina.saleadviser.integration;

import org.hibernate.annotations.DialectOverride;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aquamarina.saleadviser.SaleadviserApplication;
import ru.aquamarina.saleadviser.core.calculator.CalculatorFactory;
import ru.aquamarina.saleadviser.core.calculator.CalculatorProperties;
import ru.aquamarina.saleadviser.core.model.TableFunction;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SaleadviserApplication.class)
//@ContextConfiguration
public class CalculatorFactoryTest {

    private final CalculatorProperties calculatorProperties;
    private final CalculatorFactory calculatorFactory;

    @Autowired
    public CalculatorFactoryTest(CalculatorProperties calculatorProperties, CalculatorFactory calculatorFactory) {
        this.calculatorProperties = calculatorProperties;
        this.calculatorFactory = calculatorFactory;
    }

    @Test
    void testSpringBeanCreation() {
        CalculatorProperties.CalculatorId actual = calculatorProperties.getDefaultCalculator();
        CalculatorProperties.CalculatorId expected = CalculatorProperties.CalculatorId.HEURISTIC;
        assertSame(actual, expected);
    }

    private TableFunction prepareEmptyTableFunction() {
        return new TableFunction();
    }

}
