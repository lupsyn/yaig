package com.tigerspike.network;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright (c) 2016.
 *
 * @author enricodelzotto
 * @since 25/07/2016
 */
public class FLEndPointBuilder {
    private Context mContext;
    private String mEndPoint;

    private OkHttpClient mClient;

    public FLEndPointBuilder(Context context) {
        mContext = context;

    }

    public <S> S buildEndpoint(Class<S> serviceClass, String endpoint) {

        mEndPoint = endpoint;
        Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(mEndPoint)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gsonBuilder));


        mClient = createOkHttpClient();
        Retrofit retrofit = builder.client(mClient).build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient createOkHttpClient() {

        File httpCacheDirectory = mContext.getCacheDir();
        if (!httpCacheDirectory.exists()) {
            try {
                httpCacheDirectory.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }

        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        HttpLoggingInterceptor oggingInterceptor = new HttpLoggingInterceptor();
        oggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(oggingInterceptor)
                .build();

    }
}
