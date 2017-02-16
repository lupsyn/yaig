package com.tigerspike.business;


import com.tigerspike.business.controller.network.INetworkController;
import com.tigerspike.business.controller.network.ImagesCallBack;

/**
 * Copyright (c) 2016. SmartFocus UK Limited. All rights reserved.
 *
 * @author enricodelzotto
 * @since 10/04/16
 */
public class NetworkControllerMock implements INetworkController {



    @Override
    public void getPublicImages(ImagesCallBack callback) {

    }

    @Override
    public void getPublicImagesSearchByTag(String tags, ImagesCallBack callback) {

    }
}
