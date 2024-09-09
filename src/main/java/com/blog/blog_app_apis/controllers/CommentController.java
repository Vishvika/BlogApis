package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.CommentDto;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer userId, @PathVariable Integer postId){

        CommentDto createdComment = this.commentService.createComment(commentDto, userId, postId);

        return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted successfully", true), HttpStatus.OK);

    }

    @GetMapping("/user/{userId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Integer userId){
        List<CommentDto> comments = this.commentService.getCommentsByUser(userId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
