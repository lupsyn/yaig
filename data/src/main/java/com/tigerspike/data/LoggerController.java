/*
 * SmartEngage SDK
 *
 * Copyright (c) 2016.   All rights reserved.
 */

package com.tigerspike.data;


import android.util.Log;

import com.tigerspike.business.controller.ILoggerController;

public class LoggerController implements ILoggerController {
    @Override
    public void logD(String TAG, String msg) {
        Log.d(TAG, msg);
    }

    @Override
    public void logW(String TAG, String msg) {
        Log.w(TAG, msg);
    }

    @Override
    public void logI(String tag, String msg) {
        Log.d(tag, msg);
    }
}
