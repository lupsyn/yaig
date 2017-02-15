package com.tigerspike.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */

public class FlickrPublicFeedDTO {
        /*     "title": "Uploads from everyone",
          "link": "https:\/\/www.flickr.com\/photos\/",
          "description": "",
          "modified": "2017-02-14T18:25:06Z",
          "generator": "https:\/\/www.flickr.com",
          "items": [*/

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("generator")
    @Expose
    private String generator;
    @SerializedName("items")
    @Expose
    private List<FlickrImageDTO> imagesDTO;


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getGenerator() {
        return generator;
    }

    public List<FlickrImageDTO> getImagesDTO() {
        return imagesDTO;
    }
}
