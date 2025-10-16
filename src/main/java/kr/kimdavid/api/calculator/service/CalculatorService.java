package kr.kimdavid.api.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public String calculate(String operation, double num1, double num2) {
        System.out.println("=== 계산기 서비스 호출 ===");
        System.out.println("연산: " + operation);
        System.out.println("첫 번째 숫자: " + num1);
        System.out.println("두 번째 숫자: " + num2);

        double result;
        String operator;

        if (operation.equals("add")) {
            result = num1 + num2;
            operator = "+";
        } else if (operation.equals("sub")) {
            result = num1 - num2;
            operator = "-";
        } else if (operation.equals("mul")) {
            result = num1 * num2;
            operator = "×";
        } else if (operation.equals("div")) {
            if (num2 == 0) {
                return "0으로 나눌 수 없습니다!";
            }
            result = num1 / num2;
            operator = "÷";
        } else {
            return "지원하지 않는 연산입니다!";
        }

        return String.format("%.2f %s %.2f = %.2f", num1, operator, num2, result);
    }
}
