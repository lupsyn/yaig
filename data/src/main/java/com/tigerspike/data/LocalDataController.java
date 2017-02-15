package com.tigerspike.data;

import android.content.Context;

import com.tigerspike.business.controller.IDataController;
import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.views.MainViewState;
import com.tigerspike.data.persist.SharedDataController;
import com.tigerspike.data.utils.SharedStaticValues;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class LocalDataController implements IDataController {
    private SharedDataController mSharedPreferences;
    private final ThreadPoolExecutor mQueue;

    public LocalDataController(Context context) {
        mSharedPreferences = new SharedDataController(context);
        mQueue = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    }

    @Override
    public void storeState(final MainViewState state) {
        mQueue.execute(new Runnable() {
            @Override
            public void run() {
                mSharedPreferences.save(SharedStaticValues.FC_STATUS, state);
            }
        });
    }

    @Override
    public MainViewState getStoreState() {
        return (MainViewState) mSharedPreferences.load(MainViewState.class, SharedStaticValues.FC_STATUS);
    }

    @Override
    public void removeState() {
        mSharedPreferences.deleteInternal(SharedStaticValues.FC_STATUS);

    }
}
