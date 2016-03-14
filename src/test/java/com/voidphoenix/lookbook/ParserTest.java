package com.voidphoenix.lookbook;

import com.voidphoenix.lookbook.domain.Book;
import com.voidphoenix.lookbook.parser.ParserProvider;
import com.voidphoenix.lookbook.parser.impl.AllItEbooksParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserTest {

    final Book DONT_EXISTING_BOOK = new Book("QWEQWEQWEQWEWQE", "asdasdzxcqwesd", "SOME_RESOURCE");

    @Test
    public void testGettingFullList() {
        ParserProvider.getParsers().forEach(parser1 -> {
            AllItEbooksParser parser = new AllItEbooksParser();
            final List<Book> books = parser.getNewOnly(DONT_EXISTING_BOOK);
            Assert.assertEquals(parser.getClass() + ": There must be " + parser.getBooksPerPage() + " books in result set",
                    parser.getBooksPerPage(), books.size());
            final Set<Book> uniqueBooks = new HashSet<>(books);
            Assert.assertEquals(parser.getClass() + ": There must be unique books in result set", uniqueBooks.size(), books.size());
        });
    }

    @Test
    public void testHasNew() {
        ParserProvider.getParsers().forEach(parser1 -> {
            AllItEbooksParser parser = new AllItEbooksParser();
            final List<Book> booksAtFirstPage = parser.getNewOnly(DONT_EXISTING_BOOK);
            final Book firstBook = booksAtFirstPage.get(0);
            Assert.assertFalse(parser.getClass() + ": There is must be no books after " + firstBook, parser.hasNew(firstBook));
            Assert.assertTrue(parser.getClass() + ": Imaginary book can't be last book in site", parser.hasNew(DONT_EXISTING_BOOK));
        });
    }

    @Test
    public void testGettingNewOnly() {
        ParserProvider.getParsers().forEach(parser1 -> {
            AllItEbooksParser parser = new AllItEbooksParser();
            final List<Book> booksAtFirstPage = parser.getNewOnly(DONT_EXISTING_BOOK);
            final Book bookInTheMiddle = booksAtFirstPage.get(booksAtFirstPage.size() / 2);
            final List<Book> newBooks = parser.getNewOnly(bookInTheMiddle);
            Assert.assertFalse(parser.getClass() + ": Delimiter book existst in new list", newBooks.contains(bookInTheMiddle));
            Assert.assertTrue(parser.getClass() + ": There is " + newBooks.size() + " in new list, must be " + booksAtFirstPage.size(),
                    newBooks.size() == booksAtFirstPage.size() / 2);
        });
    }
}
