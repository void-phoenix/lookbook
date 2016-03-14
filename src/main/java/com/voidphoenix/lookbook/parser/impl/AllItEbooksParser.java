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

public class AllItEbooksParser implements Parser {

    private static final String URL = "http://www.allitebooks.com/";
    private static final String LINK_SELECTOR = "article div a";
    private static final String REL = "rel";
    private static final String BOOK_SIGN = "bookmark";
    private static final String TAG_HREF = "href";
    private static final int BOOKS_PER_PAGE = 10;

    public boolean hasNew(Book wasLastBookLastTime) {
        if (wasLastBookLastTime == null) return true;
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.select(LINK_SELECTOR);
            for (Element element : elements)  {
                if (element.hasText() && element.hasAttr(REL) && element.attr(REL).equals(BOOK_SIGN)) {
                    final String link = element.attr(TAG_HREF);
                    final String title = element.text();
                    //check are books equals
                    return !(wasLastBookLastTime.getTitle().equals(title) && wasLastBookLastTime.getLink().equals(link));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public Book getLast() {
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.select(LINK_SELECTOR);
            for (Element element : elements) {
                if (element.hasText() && element.hasAttr(REL) && element.attr(REL).equals(BOOK_SIGN)) {
                    final String link = element.attr(TAG_HREF);
                    final String title = element.text();
                    return new Book(title, link, URL);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Book> getNewOnly(Book wasLastBookLastTime) {
        final List<Book> books = new ArrayList<>(BOOKS_PER_PAGE);
        try {
            final Document doc = Jsoup.connect(URL).get();
            final Elements elements = doc.select(LINK_SELECTOR);
            for (Element element : elements) {
                if (element.hasText() && element.hasAttr(REL) && element.attr(REL).equals(BOOK_SIGN)) {
                    final String link = element.attr(TAG_HREF);
                    final String title = element.text();
                    final Book currentBook = new Book(title, link, URL);
                    if (wasLastBookLastTime != null && wasLastBookLastTime.equals(currentBook)) {
                        return books;
                    } else {
                        books.add(currentBook);
                    }
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
