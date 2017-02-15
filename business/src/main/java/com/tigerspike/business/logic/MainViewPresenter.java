package com.tigerspike.business.logic;


import com.tigerspike.business.controller.IDataController;
import com.tigerspike.business.controller.ILoggerController;
import com.tigerspike.business.controller.sharing.ISaveController;
import com.tigerspike.business.controller.sharing.ISharedCallback;
import com.tigerspike.business.controller.sharing.ISharingController;
import com.tigerspike.business.controller.network.INetworkController;
import com.tigerspike.business.controller.network.ImagesCallBack;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.views.MainViewContract;
import com.tigerspike.business.views.MainViewState;

import java.util.List;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class MainViewPresenter implements MainViewContract.Presenter {
    private final String TAG = "MainViewPresenter";
    private INetworkController mNetworkController;
    private ILoggerController mLoggerController;
    private IDataController mDataController;
    private ISharingController mSharingController;
    private MainViewContract.View mView;


    public MainViewPresenter(INetworkController networkController,
                             ILoggerController loggerController,
                             IDataController dataController,
                             ISharingController sharingController,
                             MainViewContract.View view
    ) {
        mNetworkController = networkController;
        mLoggerController = loggerController;
        mDataController = dataController;
        mSharingController = sharingController;
        mView = view;
    }

    @Override
    public void initialize() {
        retriveImages();
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
        mView.showLoading();
        mSharingController.shareImage(image, new ISharedCallback() {
            @Override
            public void onSuccess() {
                mView.hideLoading();
            }

            @Override
            public void onError(String error) {
                mView.hideLoading();
                mView.showToast(error);
            }
        });
    }

    @Override
    public void saveImage(FlickrImage image) {
        mView.showLoading();
        mSharingController.saveImage(image, new ISaveController() {
            @Override
            public void onSuccess(String savedOn) {

                mView.showToast("Image saved on: " + savedOn);
                mView.hideLoading();
            }

            @Override
            public void onError(String error) {
                mView.hideLoading();
                mView.showToast(error);
            }
        });
    }

    @Override
    public void openImage(FlickrImage image) {
        mSharingController.openImage(image);
    }

    @Override
    public void onViewAttached(Object view) {
        //mView = (MainViewContract.View) view;
        MainViewState state = mDataController.getStoreState();
        if (state != null && state.getImages() != null) {
            mView.refreshImages(state.getImages());
        } else {
            retriveImages();
        }
    }

    @Override
    public void onViewDetached(Object state) {
        if (state != null) {
            mDataController.storeState((MainViewState) state);
        }
        mView = null;
    }

    @Override
    public void onDestroyed() {
        mView = null;
    }


}
