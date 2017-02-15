package com.tigerspike.business.logic;


import com.tigerspike.business.controller.IDataController;
import com.tigerspike.business.controller.ILoggerController;
import com.tigerspike.business.controller.network.INetworkController;
import com.tigerspike.business.controller.network.ImagesCallBack;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.views.MainViewContract;

import java.util.List;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class MainViewPresenter implements MainViewContract.Presenter {
    private final String TAG = "MainViewPresenter";
    private INetworkController mNetworkController;
    private ILoggerController mLoggerController;
    private IDataController mDataController;
    private MainViewContract.View mView;

    public MainViewPresenter(INetworkController networkController,
                             ILoggerController loggerController,
                             IDataController dataController,
                             MainViewContract.View mainView) {
        mNetworkController = networkController;
        mLoggerController = loggerController;
        mDataController = dataController;
        mView = mainView;
        this.mView.setPresenter(this);
    }

    @Override
    public void retriveImages() {
        mView.showLoading();
        mNetworkController.getPublicImages(new ImagesCallBack() {
            @Override
            public void onSuccess(List<FlickrImage> publicFeed) {
                mLoggerController.logD(TAG, "Images received !");
                mView.hideLoading();
                mView.refreshImages(publicFeed);
            }

            @Override
            public void onNetworkError() {
                mLoggerController.logW(TAG, "Error on retriving images!");
                mView.hideLoading();
                mView.showToast("Network error!");
            }

            @Override
            public void onError(String error) {
                mLoggerController.logW(TAG, "Error on retriving images: " + error);
                mView.hideLoading();
                mView.showToast(error);
            }
        });
    }

    @Override
    public void retriveImage(String tags) {
        mView.showLoading();
        mNetworkController.getPublicImagesSearchByTag(tags, new ImagesCallBack() {
            @Override
            public void onSuccess(List<FlickrImage> publicFeed) {
                mLoggerController.logD(TAG, "Images received !");
                mView.hideLoading();
                mView.refreshImages(publicFeed);
            }

            @Override
            public void onNetworkError() {
                mLoggerController.logW(TAG, "Error on retriving images!");
                mView.hideLoading();
                mView.showToast("Network error!");
            }

            @Override
            public void onError(String error) {
                mLoggerController.logW(TAG, "Error on retriving images: " + error);
                mView.hideLoading();
                mView.showToast(error);
            }
        });
    }

    @Override
    public void shareImage(FlickrImage image) {

    }

    @Override
    public void saveImage(FlickrImage image) {

    }

    @Override
    public void openImage(FlickrImage image) {

    }

    @Override
    public void initialize() {
        retriveImages();
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
