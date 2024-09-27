package ru.aquamarina.saleadviser.core.calculator;

import org.mapstruct.factory.Mappers;
import ru.aquamarina.saleadviser.core.model.Prediction;
import ru.aquamarina.saleadviser.core.model.TableFunction;
import ru.aquamarina.saleadviser.core.tools.PredictionTool;

import java.time.ZonedDateTime;

public class HeuristicCalculator implements Calculator {

    private final TableFunction tableFunction;
    // number of month before date when we do prediction to compare with other years
    private final int monthsForComparison;
    private final ZonedDateTime now;

    private final PredictionTool predictionTool = Mappers.getMapper(PredictionTool.class);

    public HeuristicCalculator(TableFunction tableFunction, int monthsForComparison, ZonedDateTime now) {
        this.tableFunction = tableFunction;
        this.monthsForComparison = monthsForComparison;
        this.now = now;
    }

    public HeuristicCalculator(TableFunction tableFunction, int monthsForComparison) {
        this(tableFunction, monthsForComparison, ZonedDateTime.now());
    }

    @Override
    public Prediction predict(ZonedDateTime targetDate) {
        ZonedDateTime dateForAmount = now.minusMonths(monthsForComparison);
        double amountToCompare = tableFunction.getValueAmount(dateForAmount, now);
        ZonedDateTime yearBefore = now.minusYears(1);
        ZonedDateTime dateForAmountYearBefore = yearBefore.minusMonths(monthsForComparison);
        double amountToCompareYearBefore = tableFunction.getValueAmount(dateForAmountYearBefore, yearBefore);
        double coefficient = amountToCompare / amountToCompareYearBefore;
        ZonedDateTime targetDateYearBefore = targetDate.minusYears(1);
        double valueYearBefore = tableFunction.getValue(targetDateYearBefore);
        double prediction = valueYearBefore * coefficient;
        // todo maybe i should use double anywhere instead of float???
        return predictionTool.create(tableFunction.getProductId(), (float) prediction, targetDate, now);
    }


}
