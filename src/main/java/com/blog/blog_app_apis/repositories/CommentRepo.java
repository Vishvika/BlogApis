package com.blog.blog_app_apis.repositories;

import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
     List<Comment> findByUser(User user);

}
