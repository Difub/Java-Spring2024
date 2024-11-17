package com.example.Spring4.service;


import com.example.Spring4.dto.CategoryDTO;
import com.example.Spring4.entity.Category;
import com.example.Spring4.exception.ResourceNotFoundException;
import com.example.Spring4.mapper.CategoryMapper;
import com.example.Spring4.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.dtoToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.categoryToDto(savedCategory);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::categoryToDto)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        return categoryMapper.categoryToDto(category);
    }

    public void deleteCategory(Long id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        categoryRepository.delete(category);
    }
}