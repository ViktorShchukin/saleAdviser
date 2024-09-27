package ru.aquamarina.saleadviser.core.calculator;

import ru.aquamarina.saleadviser.config.CalculatorProperties;
import ru.aquamarina.saleadviser.core.model.TableFunction;

import java.util.Locale;
import java.util.Optional;

public class CalculatorFactory {

    private final CalculatorProperties calculatorProperties;

    public CalculatorFactory(CalculatorProperties calculatorProperties) {
        this.calculatorProperties = calculatorProperties;
    }

    public Calculator getCalculator(TableFunction tableFunction) {
        String calcName = calculatorProperties.getDefaultCalculator().toLowerCase(Locale.ROOT);
        Calculator calculator;
        return switch (calcName) {
            case "simplecalculator" -> new SimpleCalculator(tableFunction);
            case "heuristiccalculator" ->
                    new HeuristicCalculator(tableFunction, calculatorProperties.getHeuristic().getMonthToCompare());
            default -> new HeuristicCalculator(tableFunction, calculatorProperties.getHeuristic().getMonthToCompare());
        };

    }


}
