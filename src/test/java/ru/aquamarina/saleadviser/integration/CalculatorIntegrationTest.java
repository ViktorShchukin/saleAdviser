package ru.aquamarina.saleadviser.integration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aquamarina.saleadviser.SaleadviserApplication;
import ru.aquamarina.saleadviser.core.calculator.Calculator;
import ru.aquamarina.saleadviser.core.calculator.HeuristicCalculator;
import ru.aquamarina.saleadviser.core.model.Product;
import ru.aquamarina.saleadviser.core.model.SaleInMonth;
import ru.aquamarina.saleadviser.core.model.TableFunction;
import ru.aquamarina.saleadviser.core.tools.SaleTool;
import ru.aquamarina.saleadviser.service.ProductService;
import ru.aquamarina.saleadviser.service.SaleService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration()
@SpringBootTest(classes = SaleadviserApplication.class)
@Disabled
public class CalculatorIntegrationTest {

    private final Double EPS = 0.1;

    private final ProductService productService;
    private final SaleService saleService;
    private final SaleTool saleTool;

    @Autowired
    public CalculatorIntegrationTest(ProductService productService, SaleService saleService, SaleTool saleTool) {
        this.productService = productService;
        this.saleService = saleService;
        this.saleTool = saleTool;
    }

    @Test
    void testPredictionsOnExistedValues(UUID productId){
        List<Product> products = productService.getAll();
        List<SaleInMonth> saleInMonthList = saleService.getAllSaleInMonthByProductId(productId);
        int lastValue = saleInMonthList.getLast().getQuantity();
        ZonedDateTime lastValueDate = saleInMonthList.getLast().getDate();
        ZonedDateTime beforeLast = lastValueDate.minusMonths(1);
        TableFunction tableFunction = saleTool.toTableFunction(saleInMonthList);
        Calculator calculator = new HeuristicCalculator(tableFunction, 6, beforeLast);
        double calculationResult = calculator.predict(lastValueDate).getValue();
        assertTrue(
                Math.abs(lastValue - calculationResult) < EPS
        );
    }

}
