package com.example.Spring5.service;

import com.example.Spring5.entity.Book;
import com.example.Spring5.repo.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books", key = "#title + #author")
    public Book findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @CachePut(value = "books", key = "#result.id")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", key = "#id")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @CachePut(value = "books", key = "#book.id")
    public Book update(Book book) {
        return bookRepository.save(book);
    }
}