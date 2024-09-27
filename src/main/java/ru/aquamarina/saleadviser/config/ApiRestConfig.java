package ru.aquamarina.saleadviser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aquamarina.saleadviser.core.calculator.CalculatorFactory;

import java.nio.charset.Charset;

@Configuration
public class ApiRestConfig {

    @Bean
    public Charset defaultCharset(@Value("${sa.fileupload.defaultcharset:UTF-8}") String charset) {
        return Charset.forName(charset);
    }

    @Bean
    public CalculatorFactory calculatorFactory(CalculatorProperties calculatorProperties){
        return new CalculatorFactory(calculatorProperties);
    }
}
