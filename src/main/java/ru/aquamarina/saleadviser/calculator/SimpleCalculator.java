package ru.aquamarina.saleadviser.calculator;

import ru.aquamarina.saleadviser.model.Prediction;
import ru.aquamarina.saleadviser.model.TableFunction;

import java.time.ZonedDateTime;

public class SimpleCalculator implements Calculator {

    private TableFunction tableFunction;

    public SimpleCalculator() {
    }

    @Override
    public Prediction predict(ZonedDateTime targetDate) {
        return new Prediction();
    }

    public TableFunction getTableFunction() {
        return tableFunction;
    }

    public void setTableFunction(TableFunction tableFunction) {
        this.tableFunction = tableFunction;
    }
}
