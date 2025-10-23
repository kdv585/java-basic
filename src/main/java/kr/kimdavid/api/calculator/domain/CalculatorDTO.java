package kr.kimdavid.api.calculator.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorDTO {
    private String operation;
    private double num1;
    private double num2;
    
}
