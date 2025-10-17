package kr.kimdavid.api.auth.service;

import org.springframework.stereotype.Service;

import kr.kimdavid.api.auth.domain.LoginDTO;
import kr.kimdavid.api.auth.domain.LoginVO;
import kr.kimdavid.api.common.domain.Messenger;

@Service
public class LoginService {

    public Messenger login(LoginDTO loginDTO) {
        System.out.println("로그인 서비스로 돌아옴");
        System.out.println("DTO에서 서비스로 전달된 이메일 : " + loginDTO.getEmail());
        System.out.println("DTO에서 서비스로 전달된 비밀번호 : " + loginDTO.getPassword());

        // 실제 사용자 데이터 (실제로는 데이터베이스에서 조회해야 함)
        LoginVO loginVO = new LoginVO();
        loginVO.setEmail("a"); // 테스트용 이메일
        loginVO.setPassword("b"); // 테스트용 비밀번호

        System.out.println("VO에서 서비스로 전달된 이메일 : " + loginVO.getEmail());
        System.out.println("VO에서 서비스로 전달된 비밀번호 : " + loginVO.getPassword());

        int code = 0;
        String message = "";

        // 이메일과 비밀번호가 모두 일치하는 경우
        if (loginVO.getEmail().equals(loginDTO.getEmail())
                && loginVO.getPassword().equals(loginDTO.getPassword())) {
            System.out.println("로그인 성공!");
            code = 0;
            message = "로그인 성공!";
        }
        // 이메일은 일치하지만 비밀번호가 틀린 경우
        else if (loginVO.getEmail().equals(loginDTO.getEmail())
                && !loginVO.getPassword().equals(loginDTO.getPassword())) {
            System.out.println("비밀번호가 틀렸습니다.");
            code = 2;
            message = "비밀번호가 틀렸습니다.";
        }
        // 이메일이 존재하지 않는 경우
        else {
            System.out.println("존재하지 않는 이메일입니다.");
            code = 1;
            message = "존재하지 않는 이메일입니다.";
        }

        Messenger messenger = new Messenger();
        messenger.setCode(code);
        messenger.setMessage(message);
        return messenger;
    }

}
