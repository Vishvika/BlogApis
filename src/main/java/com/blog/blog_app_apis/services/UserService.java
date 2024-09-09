package com.blog.blog_app_apis.services;
import com.blog.blog_app_apis.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);

}
