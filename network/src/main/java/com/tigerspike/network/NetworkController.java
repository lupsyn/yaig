package com.tigerspike.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.tigerspike.business.controller.network.INetworkController;
import com.tigerspike.business.controller.network.ImagesCallBack;
import com.tigerspike.network.entity.FlickrPublicFeedDTO;

import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.tigerspike.network.entity.Adapter.convertListFlickrImageDto;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public class NetworkController implements INetworkController {
    private FLEndPoint mFLEndPoint;
    private Context mContext;

    public NetworkController(@NonNull Context context) {
        mContext = context;
        mFLEndPoint = new FLEndPointBuilder(context).buildEndpoint(FLEndPoint.class, FLEConfig.FL_ENDPOINT);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void getPublicImages(final ImagesCallBack callback) {
        if (!isNetworkAvailable()) {
            callback.onError("Data network not available!");
        } else {
            mFLEndPoint.getPublicImages("json", 1, null, null, null, null, null)
                    .retry(FLEConfig.FL_RETRY_VALUE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .timeout(FLEConfig.FL_RETRY_TIMEOUT, TimeUnit.SECONDS)
                    .subscribe(new Subscriber<FlickrPublicFeedDTO>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                if (((HttpException) e).response().code() == 401) {
                                    callback.onError("Auth Error");
                                } else if (((HttpException) e).response().code() == 502) {
                                    callback.onError("City not found!");
                                } else {
                                    callback.onNetworkError();
                                }
                            } else
                                callback.onNetworkError();
                        }

                        @Override
                        public void onNext(FlickrPublicFeedDTO publicFeedDTO) {
                            callback.onSuccess(convertListFlickrImageDto(publicFeedDTO.getImagesDTO()));
                        }
                    });
        }
    }

    @Override
    public void getPublicImagesSearchByTag(String tags, final ImagesCallBack callback) {
        if (!isNetworkAvailable()) {
            callback.onError("Data network not available!");
        } else {
            mFLEndPoint.getPublicImages("json", 1, null, null, tags, null, null)
                    .retry(FLEConfig.FL_RETRY_VALUE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .timeout(FLEConfig.FL_RETRY_TIMEOUT, TimeUnit.SECONDS)
                    .subscribe(new Subscriber<FlickrPublicFeedDTO>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException) {
                                if (((HttpException) e).response().code() == 401) {
                                    callback.onError("Auth Error");
                                } else if (((HttpException) e).response().code() == 502) {
                                    callback.onError("City not found!");
                                } else {
                                    callback.onNetworkError();
                                }
                            } else
                                callback.onNetworkError();
                        }

                        @Override
                        public void onNext(FlickrPublicFeedDTO publicFeedDTO) {
                            callback.onSuccess(convertListFlickrImageDto(publicFeedDTO.getImagesDTO()));
                        }
                    });
        }
    }
}
