package kr.kimdavid.api.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
public class LoginController {
    
    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam(name = "email") String email, 
                              @RequestParam(name = "password") String password) {
        
        System.out.println("id = " + email);
        System.out.println("password = " + password);
        
        // 로그인 처리 후 리다이렉트 (예: 홈페이지로)
        return "redirect:/";
    }
}
