package kr.kimdavid.api.calculator.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorVO {
    private String operation;
    private double num1;
    private double num2;
    
}
