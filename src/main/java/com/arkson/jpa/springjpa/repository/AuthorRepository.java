package com.arkson.jpa.springjpa.repository;

import com.arkson.jpa.springjpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
