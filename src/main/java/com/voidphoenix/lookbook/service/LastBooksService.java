package com.voidphoenix.lookbook.service;

import com.voidphoenix.lookbook.domain.Book;

public interface LastBooksService {

    void setLastBook(Book book);

    Book getLastBookForResouce(String resourse);
}
