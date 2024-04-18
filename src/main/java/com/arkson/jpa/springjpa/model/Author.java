package com.arkson.jpa.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(of = "id")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String genre;
    private Integer age;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author(String name, String genre, Integer age) {
        this.name = name;
        this.genre = genre;
        this.age = age;
    }

    public void addBook(final Book book) {
        if (this.books == null) {
            this.books = new TreeSet<>();
        }

        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(final Book book) {
        book.setAuthor(null);
        getBooks().remove(book);
    }
}
