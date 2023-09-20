package com.spring.lcwd.user.service.service;

import com.spring.lcwd.user.service.dto.UserDto;
import com.spring.lcwd.user.service.entity.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(User user);

    public UserDto updateUser(User user, String userId);

    public UserDto getUserByUserId(String userId);

    public List<UserDto> getAllUsers();

    public void deleteUserByUserId(String userId);
}