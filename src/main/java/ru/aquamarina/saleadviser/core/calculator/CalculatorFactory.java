package ru.aquamarina.saleadviser.core.calculator;

import ru.aquamarina.saleadviser.core.model.TableFunction;

import java.util.Locale;

public class CalculatorFactory {

    private final CalculatorProperties calculatorProperties;

    public CalculatorFactory(CalculatorProperties calculatorProperties) {
        this.calculatorProperties = calculatorProperties;
    }

    public Calculator getDefault(TableFunction tableFunction) {
        return getCalculator(tableFunction, calculatorProperties.getDefaultCalculator());
    }

    public Calculator getCalculator(TableFunction tableFunction, CalculatorProperties.CalculatorId id) {
        Calculator calculator;
        return switch (id) {
            case SIMPLE -> new SimpleCalculator(tableFunction);
            case HEURISTIC ->
                    new HeuristicCalculator(tableFunction, calculatorProperties.getHeuristic().getMonthToCompare());
        };

    }


}
