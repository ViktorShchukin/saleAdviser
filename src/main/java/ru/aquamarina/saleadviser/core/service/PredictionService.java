package ru.aquamarina.saleadviser.core.service;

import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.core.calculator.Calculator;
import ru.aquamarina.saleadviser.core.calculator.SimpleCalculator;
import ru.aquamarina.saleadviser.core.model.Prediction;
import ru.aquamarina.saleadviser.core.model.Sale;
import ru.aquamarina.saleadviser.core.tools.SaleTool;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PredictionService {

    SaleTool saleTool;

    public PredictionService(SaleTool saleTool) {
        this.saleTool = saleTool;
    }

    public Prediction get(List<Sale> saleList, ZonedDateTime targetDate) {
        Calculator calculator = new SimpleCalculator(saleTool.toTableFunction(saleList));
        return calculator.predict(targetDate);
    }
}
