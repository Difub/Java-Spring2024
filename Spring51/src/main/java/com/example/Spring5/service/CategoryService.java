package com.example.Spring5.service;

import com.example.Spring5.entity.Category;
import com.example.Spring5.repo.CategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "categories", key = "#name")
    public List<Category> findCategoriesByName(String name) {
        return categoryRepository.findAll();
    }

    public Category createOrGetCategory(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            category = new Category();
            category.setName(name);
            categoryRepository.save(category);
        }
        return category;
    }
}