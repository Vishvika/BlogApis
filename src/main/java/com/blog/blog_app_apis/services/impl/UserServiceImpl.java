package com.blog.blog_app_apis.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import com.blog.blog_app_apis.config.AppConstants;
import com.blog.blog_app_apis.entities.Role;
import com.blog.blog_app_apis.exceptions.*;
import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.payloads.UserDto;
import com.blog.blog_app_apis.repositories.RoleRepo;
import com.blog.blog_app_apis.repositories.UserRepo;
import com.blog.blog_app_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        //isse user ke pass normal password nhi rahega.
        // wo password encode hoke store ho jaega
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();

        //Lambda ki stream api use ki list of users ko List of userDtos me convert karne ko
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);


//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
