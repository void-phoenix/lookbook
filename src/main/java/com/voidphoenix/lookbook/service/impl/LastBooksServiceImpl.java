package com.voidphoenix.lookbook.service.impl;

import com.voidphoenix.lookbook.service.LastBooksService;
import com.voidphoenix.lookbook.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;


@Service
public class LastBooksServiceImpl implements LastBooksService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Book getLastBookForResouce(String resourse) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT * FROM last_books WHERE resource_url like ?",
                    new Object[]{resourse},
                    (ResultSet rs, int rowNum) -> {
                        Book foundBook = new Book();
                        foundBook.setLink(rs.getString("url"));
                        foundBook.setTitle(rs.getString("title"));
                        foundBook.setResourse(rs.getString("resource_url"));
                        return foundBook;
                    });
        } catch (Exception ex) {
            //there is no books for this resource yet
            return null;
        }
    }

    @Override
    public void setLastBook(Book book) {
        if (getLastBookForResouce(book.getResourse()) == null) {
            jdbcTemplate.update("INSERT INTO last_books (resource_url, title, url) values (?, ?, ?)",
                    book.getResourse(), book.getTitle(), book.getLink());
        } else {
            jdbcTemplate.update("UPDATE last_books SET title = ?, url = ? WHERE resource_url like ?",
                    book.getTitle(), book.getLink(), book.getResourse());
        }
    }

}
