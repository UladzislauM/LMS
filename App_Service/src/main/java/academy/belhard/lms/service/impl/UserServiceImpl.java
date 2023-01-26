package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.EmailLinkService;
import academy.belhard.lms.service.MailService;
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
    private static final String USER_NOT_ACTIVATED_MSG = "User not activated";
    private static final int TOKEN_ACTIVITY_TIME = 60 * 60;
    private static final String EMAIL_SUBJECT = "Password confirmation";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final EmailLinkService emailLinkService;

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
    public Page<UserDto> getAllByCourseAndRequestStatus(Long id,Pageable  pageable) {
        Page<User> users = userRepository.findAllByCourseAndRequestStatus(id, pageable);
        return users.map(userMapper::userToUserDto);
    }

    @Override
    public void registerUser(UserDtoForSave dto) {
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new LmsException(String.format("Email %s already exists in the database", dto.getEmail()));
        }
        User entity = userMapper.userDtoForSavingToUser(dto);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setEmail(dto.getEmail().trim());
        entity.setPassword(encodedPassword);
        entity.setRole(User.Role.STUDENT);
        entity.setActive(false);
        User created = userRepository.save(entity);
        String token = emailLinkService.generateToken(TOKEN_ACTIVITY_TIME);
        mailService.sendEmail(created.getEmail(), EMAIL_SUBJECT,
                "Please, visit link: http://localhost:8080/auth/activate/" + token +
                "/" + created.getId());
    }

    @Override
    public void activate(Long userId, String token) {
        if(!emailLinkService.isActivated(token)) {
            throw new LmsException("The token is not active. Reauthorize, please.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserAppDetails(userRepository.findByEmailActive(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_ACTIVATED_MSG)));
    }
}
