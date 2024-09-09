package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.payloads.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService   {

    CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

    void deleteComment(Integer commentId);

    List<CommentDto> getCommentsByUser(Integer userId);
}
