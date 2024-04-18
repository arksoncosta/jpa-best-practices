package com.arkson.jpa.springjpa.repository;

import com.arkson.jpa.springjpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
