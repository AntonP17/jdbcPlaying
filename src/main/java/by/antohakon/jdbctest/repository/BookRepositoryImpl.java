package by.antohakon.jdbctest.repository;

import by.antohakon.jdbctest.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class BookRepositoryImpl implements BookRepository {

    private final String url;
    private final String username;
    private final String password;
    private final String driver;
    private Connection connection;

    public BookRepositoryImpl(
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password,
            @Value("${datasource.driver}") String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
        initializeConnection();
    }

    private void initializeConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            log.info("СОЕДИНЕНИЕ НОРМ ANTOXA");
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public List<Book> findAll() {
        log.info("зашли в метод findAll ");
        List<Book> books = new ArrayList<>();

        try {
            String query = "SELECT * FROM books";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.info("выполнили запрос PrepareStatement и получили результат");

            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .build();
                log.info("НАША КНИГА БЕЗ ФИГИ ДОБАВИЛИ В СПИСОК{}", book.toString());
                books.add(book);
            }
        } catch (SQLException e) {
            log.error("ВСЕ ХЕРН ДАВАЙ ПО НОВОЙ " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book findById(Long id) {

        log.info("зашли в метод findById ");
        Book book = null;

        try {
            String query = "SELECT * FROM books WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.info("выполнили запрос PreapredStetament");

            while (resultSet.next()) {
                book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .build();
            }
        } catch (SQLException e) {
            log.error("ВСЕ ХЕРН ДАВАЙ ПО НОВОЙ " + e.getMessage());
        }
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
