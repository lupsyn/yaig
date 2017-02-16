package com.tigerspike.business.logic;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 16/02/2017
 */
public interface IPermissionCallback {
    void onAllPermissionAcquired();
    void onDenied();
}
