package com.example.praty.redditdota;

public class Comment {

    private String comment;
    private String author;
    private String updated;

    public Comment(String comment, String author, String updated) {
        this.comment = comment;
        this.author = author;
        this.updated = updated;
    }

    public Comment(){

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
