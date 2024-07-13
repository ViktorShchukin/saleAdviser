package ru.aquamarina.saleadviser.calculator;

import ru.aquamarina.saleadviser.model.Prediction;
import ru.aquamarina.saleadviser.model.TableFunction;

import java.time.ZonedDateTime;

public class SimpleCalculator implements Calculator {

    private TableFunction tableFunction;

    public SimpleCalculator() {
    }

    // todo use mapstruct for all object creation. not like here with prediction
    @Override
    public Prediction predict(ZonedDateTime targetDate) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        int derivative = tableFunction.derivative(targetDate);
        int lastSaleQuantity = tableFunction.getValue(currentTime);
        int predictionQuantity = derivative - lastSaleQuantity;
        Prediction prediction = new Prediction();
        prediction.setProductId(tableFunction.getProductId());
        prediction.setValue(predictionQuantity);
        prediction.setPredictionDate(targetDate);
        prediction.setTimestamp(currentTime);
        return prediction;
    }

    public TableFunction getTableFunction() {
        return tableFunction;
    }

    public void setTableFunction(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
    }
}
