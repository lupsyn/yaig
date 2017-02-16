package com.tigerspike.business;

import com.tigerspike.business.controller.sharing.ISaveController;
import com.tigerspike.business.controller.sharing.ISharedCallback;
import com.tigerspike.business.controller.sharing.ISharingController;
import com.tigerspike.business.entity.FlickrImage;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 16/02/2017
 */
public class ShareControllerMock implements ISharingController {
    @Override
    public void saveImage(FlickrImage image, ISaveController callback) {

    }

    @Override
    public void shareImage(FlickrImage image, ISharedCallback callback) {

    }

    @Override
    public void openImage(FlickrImage image) {

    }
}
