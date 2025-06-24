package by.antohakon.jdbctest.controller;

import by.antohakon.jdbctest.entity.Book;
import by.antohakon.jdbctest.service.BookServiceJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jdbc-template/books")
@RequiredArgsConstructor
public class BookJdbcTemplateController {

    private final BookServiceJdbcTemplate bookService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

}
