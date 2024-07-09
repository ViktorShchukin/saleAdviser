package ru.aquamarina.saleadviser.calculator;

import ru.aquamarina.saleadviser.model.Prediction;

import java.time.ZonedDateTime;

public class SimpleCalculator implements Calculator {

    @Override
    public Prediction get(ZonedDateTime targetDate){
        return new Prediction();
    }
}
