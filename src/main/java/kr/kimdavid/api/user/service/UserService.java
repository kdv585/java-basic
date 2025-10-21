package kr.kimdavid.api.user.service;

import kr.kimdavid.api.user.domain.UserDTO;
import kr.kimdavid.api.user.repository.Repository;
import kr.kimdavid.api.common.domain.Messenger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Repository repository;

    @GetMapping("/csv-test")
    public Messenger getTop5Passengers(List<UserDTO> users) {
        // 연결 용도로만 사용 - Repository 호출
        repository.printPassengerList(users);

        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("UserService에서 Repository 연결 완료: " + users.size() + "명의 데이터 처리");
        return messenger;
    }
}