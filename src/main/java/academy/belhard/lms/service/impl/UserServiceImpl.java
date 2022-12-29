package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import academy.belhard.lms.service.exceptions.NotFoundException;
import academy.belhard.lms.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_MSG = "User not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDtoForSaving dto) {
        User entity = userMapper.userDtoForSavingToUser(dto);
        entity.setRole(User.Role.STUDENT);
        entity.setActive(true);
        User created = userRepository.save(entity);
        return userMapper.userToUserDto(created);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDtoForUpdating dto) {
        User oldUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        User newUser = userMapper.userDtoForUpdatingToUser(dto);
        newUser.setActive(oldUser.isActive());
        User updated = userRepository.save(newUser);
        return userMapper.userToUserDto(updated);
    }

    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        if (!user.isActive()) {
            throw new NotFoundException(USER_NOT_FOUND_MSG);
        }
        user.setActive(false);
        User deleted = userRepository.save(user);
        return userMapper.userToUserDto(deleted);
    }

}
