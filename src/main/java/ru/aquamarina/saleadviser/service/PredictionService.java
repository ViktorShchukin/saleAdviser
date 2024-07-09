package ru.aquamarina.saleadviser.service;

import org.springframework.stereotype.Service;
import ru.aquamarina.saleadviser.calculator.Calculator;
import ru.aquamarina.saleadviser.model.Prediction;

import java.time.ZonedDateTime;

@Service
public class PredictionService {

    Calculator calculator;

    public Prediction get(ZonedDateTime targetDate) {
        return calculator.get(targetDate);
    }
}
