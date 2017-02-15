package com.tigerspike.business.controller.network;

/**
 * Copyright (c) 2017.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 09/04/16
 */
public interface ErrorCallBack {
    void onNetworkError();

    void onError(String error);
}
