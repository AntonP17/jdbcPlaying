package by.antohakon.jdbctest.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String string) {
        super(string);
    }
}
