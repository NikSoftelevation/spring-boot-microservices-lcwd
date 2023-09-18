package com.spring.lcwd.user.service.service.implementation;

import com.spring.lcwd.user.service.dto.UserDto;
import com.spring.lcwd.user.service.entity.User;
import com.spring.lcwd.user.service.exception.UserException;
import com.spring.lcwd.user.service.repository.UserRepository;
import com.spring.lcwd.user.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(User user) {

        String randomUserId = UUID.randomUUID().toString();

        user.setUserId(randomUserId);

        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(User user, String userId) {


        User userByUserId = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found with userId " + userId));

        userByUserId.setUserId(user.getUserId());
        userByUserId.setName(user.getName());
        userByUserId.setAbout(user.getAbout());
        userByUserId.setEmail(user.getEmail());


        User updatedUser = userRepository.save(userByUserId);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        User userByUserId = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found with userId " + userId));

        return modelMapper.map(userByUserId, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> getAllUsers = userRepository.findAll();

        return getAllUsers.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserByUserId(String userId) {
        userRepository.deleteById(userId);
    }
}