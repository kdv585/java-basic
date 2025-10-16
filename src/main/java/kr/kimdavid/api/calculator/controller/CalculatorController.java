package kr.kimdavid.api.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import kr.kimdavid.api.calculator.service.CalculatorService;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "calculator/calculator";
    }

    @GetMapping("/calculator/calculate")
    public String calculate(@RequestParam("operation") String operation,
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            Model model) {

        System.out.println("=== 컨트롤러에서 계산 요청 받음 ===");
        System.out.println("연산: " + operation);
        System.out.println("첫 번째 숫자: " + num1);
        System.out.println("두 번째 숫자: " + num2);

        String result = calculatorService.calculate(operation, num1, num2);
        model.addAttribute("result", result);
        model.addAttribute("operation", operation);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);

        return "calculator/calculator";
    }

    @GetMapping("/calculator/plus")
    public String plus() {
        return "calculator/plus";
    }

    @GetMapping("/calculator/minus")
    public String minus() {
        return "calculator/minus";
    }

    @GetMapping("/calculator/gob")
    public String gob() {
        return "calculator/gob";
    }

    @GetMapping("/calculator/nanum")
    public String nanum() {
        return "calculator/nanum";
    }
}
