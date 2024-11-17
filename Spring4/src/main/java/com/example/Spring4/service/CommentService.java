package com.example.Spring4.service;

import com.example.Spring4.dto.CommentDTO;
import com.example.Spring4.entity.Comment;
import com.example.Spring4.exception.ResourceNotFoundException;
import com.example.Spring4.mapper.CommentMapper;
import com.example.Spring4.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.dtoToComment(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.commentToDto(savedComment);
    }

    public List<CommentDTO> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findAllByNews_Id(newsId);
        return comments.stream()
                .map(commentMapper::commentToDto)
                .collect(Collectors.toList());
    }

    public CommentDTO getCommentById(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        return commentMapper.commentToDto(comment);
    }

    public void deleteComment(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        commentRepository.delete(comment);
    }

    public CommentDTO updateComment(CommentDTO commentDTO, Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        comment.setText(commentDTO.getText());
        comment.setUpdatedAt(java.time.LocalDateTime.now());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.commentToDto(updatedComment);
    }
}