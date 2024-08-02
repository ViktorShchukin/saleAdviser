package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.core.calculator.Calculator;
import ru.aquamarina.saleadviser.core.calculator.SimpleCalculator;
import ru.aquamarina.saleadviser.core.model.*;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PredictionService {

    private final SaleTool saleTool;
    private final SaleService saleService;

    public PredictionService(SaleTool saleTool, SaleService saleService) {
        this.saleTool = saleTool;
        this.saleService = saleService;
    }

    public Prediction get(List<Sale> saleList, ZonedDateTime targetDate) {
        Calculator calculator = new SimpleCalculator(saleTool.toTableFunction(saleList));
        return calculator.predict(targetDate);
    }

    public Prediction get(UUID productId, ZonedDateTime targetDate) {
        List<Sale> saleList = saleService.getAllByProductId(productId);
        return get(saleList, targetDate);
    }
}
