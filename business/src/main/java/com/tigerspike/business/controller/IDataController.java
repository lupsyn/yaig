/*
 * SmartEngage SDK
 *
 * Copyright (c) 2016.   All rights reserved.
 */

package com.tigerspike.business.controller;

import com.tigerspike.business.entity.FlickrImage;

public interface IDataController {

    void saveImage(FlickrImage image);

    void shareImage(FlickrImage image);

    void openImage(FlickrImage image);
}
