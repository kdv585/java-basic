package kr.kimdavid.api.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/auth/register.go")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/auth/login.go")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/calculator/calculator.go")
    public String calculator() {
        return "calculator/calculator";
    }
}
