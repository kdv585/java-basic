package kr.kimdavid.api.user.service;

import java.util.List;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.user.domain.UserDTO;

public interface UserService {
    Messenger save(UserDTO userDTO);

    Messenger saveAll(List<UserDTO> userDTOList);

    Messenger update(UserDTO userDTO);

    Messenger delete(UserDTO userDTO);

    Messenger findById(UserDTO userDTO);

    Messenger findAll();
}
