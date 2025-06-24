package by.antohakon.jdbctest.service;

import by.antohakon.jdbctest.entity.Book;
import by.antohakon.jdbctest.repository.BookRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepositoryImpl bookRepository;

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book findById(Long id) {
        Book book = bookRepository.findById(id);
        return book;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Book update(Book book) {
        return null;
    }
}
