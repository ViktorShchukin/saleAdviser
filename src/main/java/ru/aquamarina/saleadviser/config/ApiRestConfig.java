package ru.aquamarina.saleadviser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aquamarina.saleadviser.core.calculator.CalculatorFactory;
import ru.aquamarina.saleadviser.core.calculator.CalculatorProperties;

import java.nio.charset.Charset;

@Configuration
public class ApiRestConfig {

    @Bean
    public Charset defaultCharset(@Value("${sa.fileupload.defaultcharset:UTF-8}") String charset) {
        return Charset.forName(charset);
    }

    @Bean
    @ConfigurationProperties("sa.calculator")
    public CalculatorProperties calculatorProperties(){
        return new CalculatorProperties();
    }

    @Bean
    public CalculatorFactory calculatorFactory(CalculatorProperties calculatorProperties){
        return new CalculatorFactory(calculatorProperties);
    }
}
