package com.tigerspike.business.entity;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */


public class FlickrImage {
    private String title;
    private String link;
    private String externalLink;
    private String timeTaken;
    private String timePublished;
    private String author;
    private String author_id;
    private String tags;



    public FlickrImage(String title, String link, String externalLink, String timeTaken, String timePublished, String author, String author_id, String tags) {
        this.title = title;
        this.link = link;
        this.externalLink = externalLink;
        this.timeTaken = timeTaken;
        this.timePublished = timePublished;
        this.author = author;
        this.author_id = author_id;
        this.tags = tags;
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

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(String timePublished) {
        this.timePublished = timePublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
