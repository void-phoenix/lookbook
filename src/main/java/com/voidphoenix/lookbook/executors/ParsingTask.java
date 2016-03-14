package com.voidphoenix.lookbook.executors;


import com.voidphoenix.lookbook.service.LastBooksService;
import com.voidphoenix.lookbook.service.UserService;
import com.voidphoenix.lookbook.domain.Book;
import com.voidphoenix.lookbook.domain.User;
import com.voidphoenix.lookbook.parser.Parser;
import com.voidphoenix.lookbook.parser.ParserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParsingTask{

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private LastBooksService lastBooksService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Scheduled(fixedRate = 100000)
    public void notifyUsers(){
        final Map<String, List<Book>> newBooks = getNewBooks();
        if(!hasNew(newBooks)) return;
        final List<User> users = userService.getAllUsers();
        users.forEach(user -> {
            taskExecutor.execute(new SendNotificationTask(user.getUsername(), newBooks, sender));
        });
    }

    private boolean hasNew(final Map<String, List<Book>> books) {
        for( List<Book> list : books.values()) {
            if (list.size() > 0) return true;
        }
        return false;
    }

    private Map<String, List<Book>> getNewBooks(){
        final List<Parser> parsers = ParserProvider.getParsers();
        final Map<String, List<Book>> newBooks = new HashMap<>(parsers.size());
        parsers.forEach(parser -> {
            final Book lastBook = lastBooksService.getLastBookForResouce(parser.getUrl());
            final List<Book> books = parser.getNewOnly(lastBook);
            newBooks.put(parser.getUrl(), books);
            if (books.size() > 0) {
                lastBooksService.setLastBook(parser.getLast());
            }
        });
        return newBooks;
    }
}