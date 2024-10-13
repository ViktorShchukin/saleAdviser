package ru.aquamarina.saleadviser.core.calculator;

import lombok.Data;

@Data
public class CalculatorProperties {

    public CalculatorId defaultCalculator; //should be the name of the class

    public Heuristic heuristic = new Heuristic();

    @Data
    public static class Heuristic {
        public int monthToCompare;
    }

    /**
     * represent all existing calculators
     */
    public enum CalculatorId {
        SIMPLE,
        HEURISTIC
    }
}
