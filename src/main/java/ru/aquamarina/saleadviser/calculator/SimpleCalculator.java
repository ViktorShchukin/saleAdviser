package ru.aquamarina.saleadviser.calculator;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aquamarina.saleadviser.model.Prediction;
import ru.aquamarina.saleadviser.model.TableFunction;
import ru.aquamarina.saleadviser.service.tool.PredictionTool;

import java.time.ZonedDateTime;

public class SimpleCalculator implements Calculator {

    private TableFunction tableFunction;
    private final PredictionTool predictionTool = Mappers.getMapper(PredictionTool.class);

    public SimpleCalculator() {
    }

    public SimpleCalculator(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
    }

    // todo use mapstruct for all object creation. not like here with prediction
    @Override
    public Prediction predict(ZonedDateTime targetDate) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        int derivative = tableFunction.derivative(targetDate);
        int lastSaleQuantity = tableFunction.getValue(currentTime);
        int predictionQuantity = derivative - lastSaleQuantity;
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
