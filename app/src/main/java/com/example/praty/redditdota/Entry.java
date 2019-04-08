package com.example.praty.redditdota;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="entry", strict=false)
public class Entry {
    @Element(name="author",required = false)
    private Author author;

    @Element(name="content")
    private String content;

    @Element(name="id")
    private String id;

    @Element(name="title")
    private String title;

    @Element(name="updated")
    private String updated;

    public Entry() {
    }

    public Entry(Author author, String content, String id, String title, String updated) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.title = title;
        this.updated = updated;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
