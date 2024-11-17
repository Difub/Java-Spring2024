package com.example.Spring4.сontroller;

import com.example.Spring4.dto.CommentDTO;
import com.example.Spring4.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    @GetMapping("/comments/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        try {
            return commentService.getCommentById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDTO updateComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id) {
        try {
            return commentService.updateComment(commentDTO, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}