package by.antohakon.jdbctest.repository;

import by.antohakon.jdbctest.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    Book update(Book book);

}
