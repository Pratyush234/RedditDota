package com.example.praty.redditdota;

public class Post {

    private String author;
    private String date_updated;
    private String title;
    private String postURL;
    private String thumbnailURL;

    public Post(String author, String date_updated, String title, String postURL, String thumbnailURL) {
        this.author = author;
        this.date_updated = date_updated;
        this.title = title;
        this.postURL = postURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
