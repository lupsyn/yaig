package com.tigerspike.business.views;

import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.logic.IPermissionCallback;

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

        void requirePermission(IPermissionCallback callback);

    }


    public interface Presenter extends BasePresenter {

        void initialize();

        void retriveImages();

        void retriveImage(String tags);

        void shareImage(FlickrImage image);

        void openImage(FlickrImage image);

        void saveImage(FlickrImage image);
    }

    public interface BaseState {
    }

    public interface MainViewState extends BaseState {
        List<FlickrImage> getImages();
    }

}
