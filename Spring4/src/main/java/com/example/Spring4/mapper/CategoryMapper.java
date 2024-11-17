package com.example.Spring4.mapper;

import com.example.Spring4.dto.CategoryDTO;
import com.example.Spring4.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CategoryDTO categoryToDto(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Category dtoToCategory(CategoryDTO categoryDTO);
}