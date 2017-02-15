/*
 * SmartEngage SDK
 *
 * Copyright (c) 2016.   All rights reserved.
 */

package com.tigerspike.business.controller;

public interface ILoggerController {

    void logD(String TAG, String message);

    void logW(String tag, String msg);

    void logI(String tag, String msg);
}
