package com.voidphoenix.lookbook.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Book {

    private String title;
    private String link;
    private String resourse;

    public Book(){}

    public Book(String title, String link, String resourse) {
        this.title = title;
        this.link = link;
        this.resourse = resourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResourse() {
        return resourse;
    }

    public void setResourse(String resourse) {
        this.resourse = resourse;
    }

    @Override
    public String toString() {
        return this.title + " -> " + this.link;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) return false;
        Book that = (Book) obj;
        return new EqualsBuilder()
                .append(title, that.title)
                .append(link, that.link)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(title)
                .append(link)
                .toHashCode();
    }
}
