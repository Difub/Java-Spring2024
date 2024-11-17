package com.example.Spring4.mapper;

import com.example.Spring4.dto.CommentDTO;
import com.example.Spring4.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "news.id", target = "newsId")
    CommentDTO commentToDto(Comment comment);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "newsId", target = "news.id")
    Comment dtoToComment(CommentDTO commentDTO);
}