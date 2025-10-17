package kr.kimdavid.api.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import kr.kimdavid.api.auth.domain.LoginDTO;
import kr.kimdavid.api.auth.service.LoginService;
import kr.kimdavid.api.common.domain.Messenger;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        System.out.println("컨트롤러로 들어옴");
        System.out.println("화면에서 컨트롤러로 전달된 이메일 : " + email);
        System.out.println("화면에서 컨트롤러로 전달된 비밀번호 : " + password);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        Messenger messenger = loginService.login(loginDTO);

        System.out.println("서비스에서 컨트롤러로 리턴한 코드 : " + messenger.getCode());
        System.out.println("서비스에서 컨트롤러로 리턴한 메시지 : " + messenger.getMessage());

        model.addAttribute("messenger", messenger);

        return "auth/login";
    }
}