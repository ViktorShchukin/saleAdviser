package ru.aquamarina.saleadviser.core.calculator;

import org.mapstruct.factory.Mappers;
import ru.aquamarina.saleadviser.core.model.Prediction;
import ru.aquamarina.saleadviser.core.model.TableFunction;
import ru.aquamarina.saleadviser.core.tools.PredictionTool;

import java.time.ZonedDateTime;

public class SimpleCalculator implements Calculator {

    private TableFunction tableFunction;
    private final PredictionTool predictionTool = Mappers.getMapper(PredictionTool.class);

    public SimpleCalculator() {
    }

    public SimpleCalculator(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
    }

    @Override
    public Prediction predict(ZonedDateTime targetDate) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        float derivative = tableFunction.derivative(targetDate);
        float lastSaleQuantity = tableFunction.getValue(currentTime);
        float predictionQuantity = derivative - lastSaleQuantity;
        return predictionTool.create(
                tableFunction.getProductId(),
                predictionQuantity,
                targetDate,
                currentTime);
    }

    public TableFunction getTableFunction() {
        return tableFunction;
    }

    public void setTableFunction(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
    }
}
