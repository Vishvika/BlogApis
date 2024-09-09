package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.exceptions.ApiException;
import com.blog.blog_app_apis.payloads.JwtAuthRequest;
import com.blog.blog_app_apis.payloads.JwtAuthResponse;
import com.blog.blog_app_apis.payloads.UserDto;
import com.blog.blog_app_apis.security.JwtTokenHelper;
import com.blog.blog_app_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    // ye password authenticate karne ke liye
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            //we'll get username password through this
            ){

        //ab username and password milgya,so to authenticate that
        this.authenticate1(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
               UserDto registeredUser = this.userService.registerNewUser(userDto);
               return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    private void authenticate1(String username, String password) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch(Exception e ){
            new ApiException("Invalid username or password");
        }

    }
}
