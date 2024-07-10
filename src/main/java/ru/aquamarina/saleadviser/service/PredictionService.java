package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.calculator.Calculator;
import ru.aquamarina.saleadviser.calculator.SimpleCalculator;
import ru.aquamarina.saleadviser.model.Prediction;
import ru.aquamarina.saleadviser.model.Sale;
import ru.aquamarina.saleadviser.model.TableFunction;
import ru.aquamarina.saleadviser.service.tool.SaleTool;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PredictionService {

    SaleTool saleTool;

    public PredictionService(SaleTool saleTool) {
        this.saleTool = saleTool;
    }

    public Prediction get(List<Sale> saleList, ZonedDateTime targetDate) {
        SimpleCalculator calculator = new SimpleCalculator();
        calculator.setTableFunction(saleTool.toTableFunction(saleList));
        return calculator.predict(targetDate);
    }
}
