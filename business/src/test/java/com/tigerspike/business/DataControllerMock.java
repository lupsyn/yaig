package com.tigerspike.business;

import com.tigerspike.business.controller.IDataController;
import com.tigerspike.business.views.MainViewState;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 16/02/2017
 */
public class DataControllerMock implements IDataController {
    @Override
    public void storeState(MainViewState state) {

    }

    @Override
    public MainViewState getStoreState() {
        return null;
    }

    @Override
    public void removeState() {

    }
}
