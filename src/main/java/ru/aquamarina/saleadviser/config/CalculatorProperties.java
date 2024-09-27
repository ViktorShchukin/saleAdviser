package ru.aquamarina.saleadviser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("sa.calculator")
public class CalculatorProperties {

    public String defaultCalculator; //should be the name of the class

    public Heuristic heuristic = new Heuristic();

    @Data
    public static class Heuristic {
        public int monthToCompare;
    }
}
