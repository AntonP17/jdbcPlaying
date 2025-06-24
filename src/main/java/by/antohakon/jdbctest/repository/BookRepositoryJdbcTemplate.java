package by.antohakon.jdbctest.repository;

import by.antohakon.jdbctest.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepositoryJdbcTemplate implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> findAll() {
        log.info("зашли в метод findAll jdbcTemplate");

        String sql = "select * from books";

        log.info("достали данные из БД");

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book findById(Long id) throws EmptyResultDataAccessException {
        String sql = "select * from books where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    @Override
    public Book save(Book book) {

        String sql = "insert into books (title, author) values (?, ?)";

        int id = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor());

        Book saveBook = Book.builder()
                .id(id)
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();

        return saveBook;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from books where id = ?", id);
    }

    @Override
    public Book update(Book book) {
        return null;
    }
}
