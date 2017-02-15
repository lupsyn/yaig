package com.tigerspike.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tigerspike.network.utils.DateUtil;

/**
 * Copyright (c) 2017. All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public class FlickrImageDTO implements Comparable<FlickrImageDTO> {

/*          "title": "\u1ea2nh cua toi",
            "link": "https:\/\/www.flickr.com\/photos\/152084843@N02\/32059283744\/",
			"media": {"m":"https:\/\/farm1.staticflickr.com\/378\/32059283744_6c84e2e642_m.jpg"},
			"date_taken": "2017-02-03T19:18:08-08:00",
			"description": "",
			"published": "2017-02-14T18:25:06Z",
			"author": "nobody@flickr.com (\"tringuyen45\")",
			"author_id": "152084843@N02",
			"tags": ""*/

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("media")
    @Expose
    private FlickrMediaDTO directMedia;

    @SerializedName("date_taken")
    @Expose
    private String date_taken;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("published")
    @Expose
    private String published;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("author_id")
    @Expose
    private String author_id;

    @SerializedName("tags")
    @Expose
    private String tags;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public FlickrMediaDTO getDirectMedia() {
        return directMedia;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public String getDescription() {
        return description;
    }

    public String getPublished() {
        return published;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public String getTags() {
        return tags;
    }

    @Override
    public int compareTo(FlickrImageDTO o) {
        if (DateUtil.parseDate(getDate_taken()) == null || DateUtil.parseDate(o.getDate_taken()) == null)
            return 0;
        return DateUtil.parseDate(o.getDate_taken()).compareTo(DateUtil.parseDate(getDate_taken()));
        //Descending order
    }
}
