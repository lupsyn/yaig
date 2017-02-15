package com.tigerspike.business.controller.network;

import com.tigerspike.business.entity.FlickrImage;

import java.util.List;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 14/02/2017
 */
public interface ImagesCallBack extends ErrorCallBack {
    void onSuccess(List<FlickrImage> publicFeed);
}
