package com.tigerspike.business.views;

import com.tigerspike.business.entity.FlickrImage;

import java.util.List;

/**
 * Copyright (c) 2017.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class MainViewContract {
    public interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void refreshImages(List<FlickrImage> imageList);

        void showToast(String msg);

    }

    public interface Presenter extends BasePresenter {

        void retriveImages();

        void retriveImage(String tags);

        void shareImage(FlickrImage image);

        void saveImage(FlickrImage image);

        void openImage(FlickrImage image);

    }
}
