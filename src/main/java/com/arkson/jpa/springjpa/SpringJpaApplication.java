package com.arkson.jpa.springjpa;

import com.arkson.jpa.springjpa.model.Author;
import com.arkson.jpa.springjpa.model.Book;
import com.arkson.jpa.springjpa.repository.AuthorRepository;
import com.arkson.jpa.springjpa.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;

@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner runner(AuthorRepository authorRepository,
                                    BookRepository bookRepository) {
        return args -> {
            var author = new Author("Shakespare", "Male", 120);
            var book1 = new Book("A cabana", "1927617892");
            var book2 = new Book("O dia depois de amanhã", "3434343434");
            var book3 = new Book("Interstellar", "3423232323");
            var book4 = new Book("O poder do hábito", "1243546789");
            author.addBook(book1);
            author.addBook(book2);
            author.addBook(book3);
            author.addBook(book4);

            authorRepository.save(author);

            System.out.println("After saving");

            authorRepository.findAll()
                    .forEach(System.out::println);

            author.getBooks().forEach(System.out::println);

            bookRepository.findOne(Example.of(book1))
                    .ifPresent(book -> author.removeBook(book));

            System.out.println("After removing book 1");

            authorRepository.save(author);

            authorRepository.findOne(Example.of(author))
                    .ifPresent(a -> System.out.println(a.getBooks().size()));
        };
    }
}
