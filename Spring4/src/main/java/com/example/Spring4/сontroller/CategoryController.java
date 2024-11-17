package com.example.Spring4.сontroller;

import com.example.Spring4.dto.CategoryDTO;
import com.example.Spring4.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/categories")
    public Iterable<CategoryDTO> getAllCategories(
            @PageableDefault(sort = "id") Pageable pageable) {
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/categories/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        try {
            return categoryService.getCategoryById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}