package com.example.Spring4.service;

import com.example.Spring4.dto.NewsDTO;
import com.example.Spring4.entity.News;
import com.example.Spring4.exception.ResourceNotFoundException;
import com.example.Spring4.mapper.NewsMapper;
import com.example.Spring4.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = newsMapper.dtoToNews(newsDTO);
        News savedNews = newsRepository.save(news);
        return newsMapper.newsToDto(savedNews);
    }

    public Page<NewsDTO> getAllNews(Pageable pageable) {
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.map(newsMapper::newsToDto);
    }

    public Page<NewsDTO> getNewsByCategoryAndAuthor(Long categoryId, Long authorId, Pageable pageable) {
        Page<News> newsPage = newsRepository.findByCategoryAndAuthor(categoryId, authorId, pageable);
        return newsPage.map(newsMapper::newsToDto);
    }

    public NewsDTO getNewsById(Long id) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News with id " + id + " not found"));
        return newsMapper.newsToDto(news);
    }

    public void deleteNews(Long id) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News with id " + id + " not found"));
        newsRepository.delete(news);
    }

    public NewsDTO updateNews(NewsDTO newsDTO, Long id) throws ResourceNotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News with id " + id + " not found"));
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setUpdatedAt(java.time.LocalDateTime.now());
        News updatedNews = newsRepository.save(news);
        return newsMapper.newsToDto(updatedNews);
    }
}
