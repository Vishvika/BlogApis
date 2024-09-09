package com.blog.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_app_apis.entities.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
