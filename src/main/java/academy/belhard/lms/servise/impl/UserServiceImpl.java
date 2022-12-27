package academy.belhard.lms.servise.impl;

import academy.belhard.lms.dto.UserDto;
import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.mapper.UserMapper;
import academy.belhard.lms.model.Role;
import academy.belhard.lms.model.User;
import academy.belhard.lms.repository.UserRepository;
import academy.belhard.lms.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(UserDtoForSaving userDtoForSaving) {
        User user = userMapper.userDtoForSavingToUser(userDtoForSaving);
        user.setRole(Role.valueOf("STUDENT"));
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> projects = userRepository.findAll();
        return projects.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User не найден"));
        userDto.setId(id);
        User user = userMapper.userDtoToUser(userDto);
        return userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
