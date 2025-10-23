package kr.kimdavid.api.calculator.service;

import kr.kimdavid.api.calculator.domain.CalculatorDTO;

public interface CalculatorService {

    public int add(CalculatorDTO calculatorDTO);
    public int subtract(CalculatorDTO calculatorDTO);
    public int multiply(CalculatorDTO calculatorDTO);
    public int divide(CalculatorDTO calculatorDTO);
    
}
