package kr.kimdavid.api.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kimdavid.api.auth.domain.LoginDTO;
import kr.kimdavid.api.auth.service.LoginService;
import kr.kimdavid.api.common.domain.Messenger;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String processLogin(@RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "password", required = false) String password) {

        // 파라미터가 없으면 로그인 폼을 보여줌
        if (email == null || password == null) {
            System.out.println("로그인 폼 요청됨");
            return "auth/login";
        }

        // 파라미터가 있으면 로그인 처리
        System.out.println("=== GET 로그인 요청 처리 ===");
        System.out.println("이메일: " + email);
        System.out.println("비밀번호: " + password);

        try {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setEmail(email);
            loginDTO.setPassword(password);

            Messenger messenger = loginService.login(loginDTO);
            System.out.println("로그인 결과 코드: " + messenger.getCode());
            System.out.println("로그인 결과 메시지: " + messenger.getMessage());

            // 로그인 결과에 따른 처리
            if (messenger.getCode() == 0) {
                System.out.println("로그인 성공!");
                return "redirect:/";
            } else if (messenger.getCode() == 2) {
                System.out.println("비밀번호가 틀렸습니다.");
                return "redirect:/login?error=password";
            } else if (messenger.getCode() == 1) {
                System.out.println("존재하지 않는 이메일입니다.");
                return "redirect:/login?error=email";
            } else {
                System.out.println("알 수 없는 오류가 발생했습니다.");
                return "redirect:/login?error=unknown";
            }
        } catch (Exception e) {
            System.out.println("로그인 처리 중 오류: " + e.getMessage());
            return "redirect:/login?error=true";
        }
    }
}
