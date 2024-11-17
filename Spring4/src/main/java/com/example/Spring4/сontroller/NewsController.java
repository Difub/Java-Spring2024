package com.example.Spring4.сontroller;

import com.example.Spring4.dto.NewsDTO;
import com.example.Spring4.service.NewsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/news")
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.createNews(newsDTO);
    }

    @GetMapping("/news")
    public Iterable<NewsDTO> getAllNews(
            @PageableDefault(sort = "id") Pageable pageable) {
        return newsService.getAllNews(pageable);
    }

    @GetMapping("/news/category/{categoryId}/author/{authorId}")
    public Iterable<NewsDTO> getNewsByCategoryAndAuthor(
            @PathVariable Long categoryId,
            @PathVariable Long authorId,
            @PageableDefault(sort = "id") Pageable pageable) {
        return newsService.getNewsByCategoryAndAuthor(categoryId, authorId, pageable);
    }

    @GetMapping("/news/{id}")
    public NewsDTO getNewsById(@PathVariable Long id) {
        try {
            return newsService.getNewsById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/news/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDTO updateNews(@RequestBody NewsDTO newsDTO, @PathVariable Long id) {
        try {
            return newsService.updateNews(newsDTO, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/news/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable Long id) {
        try {
            newsService.deleteNews(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}