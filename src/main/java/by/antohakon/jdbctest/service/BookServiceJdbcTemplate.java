package by.antohakon.jdbctest.service;

import by.antohakon.jdbctest.entity.Book;
import by.antohakon.jdbctest.exceptions.BookNotFoundException;
import by.antohakon.jdbctest.repository.BookRepositoryJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceJdbcTemplate implements BookService {

    private final BookRepositoryJdbcTemplate bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book update(Book book) {
        return null;
    }
}
