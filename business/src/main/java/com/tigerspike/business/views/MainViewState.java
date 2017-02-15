package com.tigerspike.business.views;

import com.tigerspike.business.entity.FlickrImage;

import java.util.List;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class MainViewState implements MainViewContract.MainViewState {
    private List<FlickrImage> images;

    public MainViewState(List<FlickrImage> images) {
        this.images = images;
    }

    @Override
    public List<FlickrImage> getImages() {
        return images;

    }

    public void setImages(List<FlickrImage> images) {
        this.images = images;
    }
}
