package com.voidphoenix.lookbook.parser.impl;

import com.voidphoenix.lookbook.domain.Book;
import com.voidphoenix.lookbook.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItEbooksParser implements Parser {

    private static final String URL = "http://it-ebooks.info";
    private static final String CLASS_TOP = "top";
    private static final String ATTR_HREF = "href";
    private static final int BOOKS_PER_PAGE = 10;

    @Override
    public boolean hasNew(Book wasLastBookLastTime) {
        if (wasLastBookLastTime == null) return true;
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.getElementsByClass(CLASS_TOP);
            final Element root = elements.get(2);
            final Elements children = root.getElementsByAttribute(ATTR_HREF);
            for (Element element : children) {
                if (element.hasText()) {
                    final Book current = new Book(element.text(), URL + element.attr(ATTR_HREF), getUrl());
                    return !(current.equals(wasLastBookLastTime));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Book getLast() {
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.getElementsByClass(CLASS_TOP);
            final Element root = elements.get(2);
            final Elements children = root.getElementsByAttribute(ATTR_HREF);
            for (Element element : children) {
                if (element.hasText()) {
                    return new Book(element.text(), URL + element.attr(ATTR_HREF), getUrl());
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getNewOnly(Book wasLastBookLastTime) {
        final List<Book> books = new ArrayList<>(BOOKS_PER_PAGE);
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.getElementsByClass(CLASS_TOP);
            final Element root = elements.get(2);
            final Elements children = root.getElementsByAttribute(ATTR_HREF);
           for (Element element : children) {
                if (element.hasText()) {
                    final Book current = new Book(element.text(), URL + element.attr(ATTR_HREF), getUrl());
                    if (wasLastBookLastTime != null && current.equals(wasLastBookLastTime)) return books;
                    books.add(current);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public int getBooksPerPage() {
        return BOOKS_PER_PAGE;
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
