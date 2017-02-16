package com.tigerspike.business;

import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.logic.IPermissionCallback;
import com.tigerspike.business.views.MainViewContract;

import java.util.List;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 16/02/2017
 */
public class MockView implements MainViewContract.View {
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void refreshImages(List<FlickrImage> imageList) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void requirePermission(IPermissionCallback callback) {

    }

    @Override
    public void setPresenter(MainViewContract.Presenter presenter) {

    }
}
