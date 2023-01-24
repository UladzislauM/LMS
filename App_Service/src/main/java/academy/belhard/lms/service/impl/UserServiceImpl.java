package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "User not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDtoForSave dto) {
        Optional<User> existing = userRepository.findByEmailActive(dto.getEmail());
        if (existing.isPresent()) {
            throw new LmsException(String.format("Email %s already exists in the database", dto.getEmail()));
        }
        User entity = userMapper.userDtoForSavingToUser(dto);
        entity.setRole(User.Role.STUDENT);
        entity.setActive(true);
        User created = userRepository.save(entity);
        return userMapper.userToUserDto(created);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDtoForUpdate dto) {
        Optional<User> existing = userRepository.findByEmailActive(dto.getEmail());
        if (existing.isPresent() && !existing.get().getId().equals(dto.getId())) {
            throw new LmsException(String.format("Email %s already exists in the database", dto.getEmail()));
        }
        User oldUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        User newUser = userMapper.userDtoForUpdatingToUser(dto);
        newUser.setActive(oldUser.isActive());
        User updated = userRepository.save(newUser);
        return userMapper.userToUserDto(updated);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        if (!user.isActive()) {
            throw new NotFoundException(USER_NOT_FOUND_MSG);
        }
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDto registration(UserDtoForSave dto) {
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new LmsException(String.format("Email %s already exists in the database", dto.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User entity = userMapper.userDtoForSavingToUser(dto);
        entity.setPassword(encodedPassword);
        entity.setRole(User.Role.STUDENT);
        entity.setActive(true);
        User created = userRepository.save(entity);
        return userMapper.userToUserDto(created);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserAppDetails(userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG)));
    }
}
