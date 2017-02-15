package com.tigerspike.business.controller.sharing;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public interface ISharedCallback {

    public void onSuccess();

    public void onError(String error);

}
