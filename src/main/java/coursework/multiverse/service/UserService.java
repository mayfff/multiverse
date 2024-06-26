package coursework.multiverse.service;

import coursework.multiverse.dto.UserDto;
import coursework.multiverse.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);

}
