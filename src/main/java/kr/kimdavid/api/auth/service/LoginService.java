package kr.kimdavid.api.auth.service;

import org.springframework.stereotype.Service;

import kr.kimdavid.api.auth.domain.LoginDTO;
import kr.kimdavid.api.auth.domain.LoginVO;

@Service
public class LoginService {

    public int login(LoginDTO loginDTO) {
        System.out.println("로그인 서비스로 돌아옴");
        System.out.println("DTO에서 서비스로 전달된 이메일 : " + loginDTO.getEmail());
        System.out.println("DTO에서 서비스로 전달된 비밀번호 : " + loginDTO.getPassword());

        LoginVO loginVO = new LoginVO();

        System.out.println("VO에서 서비스로 전달된 이메일 : " + loginVO.getEmail());
        System.out.println("VO에서 서비스로 전달된 비밀번호 : " + loginVO.getPassword());
        
        // 이메일과 비밀번호가 모두 일치하는 경우
        if (loginVO.getEmail().equals(loginDTO.getEmail()) 
            && loginVO.getPassword().equals(loginDTO.getPassword())) {
            System.out.println("로그인 성공!");
            return 0;
        }
        // 이메일은 일치하지만 비밀번호가 틀린 경우
        else if (loginVO.getEmail().equals(loginDTO.getEmail()) 
                 && !loginVO.getPassword().equals(loginDTO.getPassword())) {
            System.out.println("비밀번호가 틀렸습니다.");
            return 2;
        }
        // 이메일이 존재하지 않는 경우
        else {
            System.out.println("존재하지 않는 이메일입니다.");
            return 1;
        }
    }

    Messenger messenger = new Messenger();
    Messenger.setCode(0);
    Messenger.setMessage("로그인 성공!");
    return messenger;

}
