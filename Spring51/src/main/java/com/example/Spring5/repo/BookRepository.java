package com.example.Spring5.repo;

import com.example.Spring5.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitleAndAuthor(String title, String author);
}