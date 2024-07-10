package ru.aquamarina.saleadviser.calculator;

import ru.aquamarina.saleadviser.model.Prediction;

import java.time.ZonedDateTime;

public interface Calculator {

    Prediction predict(ZonedDateTime targetDate);
}
