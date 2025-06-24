package by.antohakon.jdbctest.service;

import by.antohakon.jdbctest.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    Book update(Book book);

}
