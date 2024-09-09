package com.blog.blog_app_apis.payloads;

import com.blog.blog_app_apis.entities.Category;
import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> comments = new ArrayList<>();

}
