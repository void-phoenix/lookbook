package com.voidphoenix.lookbook.parser;


import com.voidphoenix.lookbook.domain.Book;

import java.util.List;

public interface Parser {

    boolean hasNew(Book wasLastBookLastTime);

    Book getLast();

    List<Book> getNewOnly(Book wasLastBookLastTime);

    int getBooksPerPage();

    String getUrl();
}
