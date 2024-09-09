package com.blog.blog_app_apis.payloads;

import com.blog.blog_app_apis.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is invalid")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 15, message = "Password must be minimum of 3 characters and max of 15")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}
