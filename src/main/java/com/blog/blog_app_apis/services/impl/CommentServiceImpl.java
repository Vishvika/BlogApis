package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.entities.Post;
import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.payloads.CommentDto;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.repositories.CommentRepo;
import com.blog.blog_app_apis.repositories.PostRepo;
import com.blog.blog_app_apis.repositories.UserRepo;
import com.blog.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;


    @Override
    public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
        this.commentRepo.delete(comment);

    }

    @Override
    public List<CommentDto> getCommentsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
        List<Comment> comments = this.commentRepo.findByUser(user);

        List<CommentDto> commentDtos = comments.stream().map((post) -> this.modelMapper.map(post, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

}
