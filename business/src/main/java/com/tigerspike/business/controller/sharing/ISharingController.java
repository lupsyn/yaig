package com.tigerspike.business.controller.sharing;

import com.tigerspike.business.entity.FlickrImage;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public interface ISharingController {

    void saveImage(FlickrImage image, ISaveController callback);

    void shareImage(FlickrImage image, ISharedCallback callback);

    void openImage(FlickrImage image);

}
