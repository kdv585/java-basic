package kr.kimdavid.api.user.service;

import kr.kimdavid.api.user.domain.UserDTO;
import kr.kimdavid.api.user.repository.Repository;
import kr.kimdavid.api.common.domain.Messenger;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Repository repository;

    public Messenger getTop5Passengers(List<UserDTO> users) {
        // 연결 용도로만 사용 - Repository 호출
        Messenger messenger = repository.printPassengerListMessage(users);
        return messenger;
    }
}