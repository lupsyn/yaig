package com.tigerspike.business;

import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.logic.MainViewPresenter;
import com.tigerspike.business.views.MainViewContract;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 16/02/2017
 */
public class MockPresenter implements MainViewContract.Presenter {
    @Override
    public void initialize() {

    }

    @Override
    public void retriveImages() {

    }

    @Override
    public void retriveImage(String tags) {

    }

    @Override
    public void shareImage(FlickrImage image) {

    }

    @Override
    public void openImage(FlickrImage image) {

    }

    @Override
    public void saveImage(FlickrImage image) {

    }

    @Override
    public void onViewAttached(Object view) {

    }

    @Override
    public void onViewDetached(Object state) {

    }

    @Override
    public void onDestroyed() {

    }
}
