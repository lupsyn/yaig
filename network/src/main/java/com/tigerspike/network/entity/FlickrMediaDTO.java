package com.tigerspike.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public class FlickrMediaDTO {
    @SerializedName("m")
    @Expose
    private String link;

    public String getLink() {
        return link;
    }
}
