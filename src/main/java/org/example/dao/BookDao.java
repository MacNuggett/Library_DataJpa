package org.example.dao;

import org.example.models.Book;
import org.example.models.BookMapper;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public List<Book> getBooks(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE personid=?", new Object[]{id}, new BookMapper());
    }

    public Person getPerson(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void setPerson(int personId, int bookId){
        jdbcTemplate.update("UPDATE book SET personid=? WHERE id=?", personId, bookId);
    }

    public void deletePerson(int bookId){
        jdbcTemplate.update("UPDATE book SET personid=NULL WHERE id=?", bookId);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(),book.getYear());
    }

    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=?  WHERE id=?", book.getTitle(), book.getAuthor(),book.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
