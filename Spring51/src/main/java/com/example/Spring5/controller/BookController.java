package com.example.Spring5.controller;

import com.example.Spring5.entity.Book;
import com.example.Spring5.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{title}/{author}")
    public Book getByTitleAndAuthor(@PathVariable String title, @PathVariable String author) {
        return bookService.findByTitleAndAuthor(title, author);
    }
}