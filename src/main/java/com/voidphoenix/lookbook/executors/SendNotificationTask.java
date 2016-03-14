package com.voidphoenix.lookbook.executors;

import com.voidphoenix.lookbook.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

public class SendNotificationTask implements Runnable {

    private static final String SUBJECT = "Появились новые книги";
    private static final String FROM = "inquiring.the.void@gmail.com";


    private final String email;
    private final Map<String, List<Book>> books;
    private final JavaMailSender javaMailSender;

    public SendNotificationTask(String email, Map<String, List<Book>> books, JavaMailSender javaMailSender) {
        this.email = email;
        this.books = books;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void run() {
        StringBuilder text = new StringBuilder();
        for (List<Book> bookList : books.values()){
            bookList.forEach( book -> text.append(book).append("\n"));
        }

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(email);
            helper.setReplyTo(FROM);
            helper.setFrom(FROM);
            helper.setSubject(SUBJECT);
            helper.setText(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }
}
