package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.core.calculator.Calculator;
import ru.aquamarina.saleadviser.core.calculator.CalculatorFactory;
import ru.aquamarina.saleadviser.core.model.*;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PredictionService {

    private final SaleTool saleTool;
    private final SaleService saleService;
    private final CalculatorFactory calculatorFactory;

    public PredictionService(SaleTool saleTool, SaleService saleService, CalculatorFactory calculatorFactory) {
        this.saleTool = saleTool;
        this.saleService = saleService;
        this.calculatorFactory = calculatorFactory;
    }

    public Prediction getPrediction(UUID productId, ZonedDateTime targetDate) {
        List<SaleInMonth> saleList = saleService.getAllSaleInMonthByProductId(productId);
        TableFunction tableFunction = saleTool.toTableFunction(saleList);
        return getPrediction(tableFunction, targetDate);
    }

    private Prediction getPrediction(TableFunction tableFunction, ZonedDateTime targetDate) {
        Calculator calculator = calculatorFactory.getCalculator(tableFunction);
        return calculator.predict(targetDate);
    }


}
