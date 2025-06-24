package by.antohakon.jdbctest.controller;

import by.antohakon.jdbctest.entity.Book;
import by.antohakon.jdbctest.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jdbc/books")
@RequiredArgsConstructor
public class BookJdbcController {

    private final BookServiceImpl bookServiceImpl;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks() {
        return bookServiceImpl.findAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable Long bookId) {
        return bookServiceImpl.findById(bookId);
    }
}
