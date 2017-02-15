/*
 * SmartEngage SDK
 *
 * Copyright (c) 2016.   All rights reserved.
 */

package com.tigerspike.business.controller;

import com.tigerspike.business.entity.FlickrImage;
import com.tigerspike.business.views.MainViewState;

import java.util.List;

public interface IDataController {

    void storeState(MainViewState state);

    MainViewState getStoreState();

    void removeState();
}
