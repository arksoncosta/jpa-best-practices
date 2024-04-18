package com.arkson.jpa.springjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "title")
@ToString(exclude = "author")
public class Book implements Comparable<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.getTitle());
    }
}
