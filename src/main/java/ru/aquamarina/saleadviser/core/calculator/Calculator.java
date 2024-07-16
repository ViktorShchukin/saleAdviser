package ru.aquamarina.saleadviser.core.calculator;

import ru.aquamarina.saleadviser.core.model.Prediction;

import java.time.ZonedDateTime;

public interface Calculator {

    Prediction predict(ZonedDateTime targetDate);
}
