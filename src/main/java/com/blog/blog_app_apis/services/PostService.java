package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.entities.Post;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize);
    PostDto getSinglePost(Integer postId);

    //Get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //Get all posts by users
    List<PostDto> getPostsByUser(Integer userId);

    List<Post> searchPosts(String keyword);

}
