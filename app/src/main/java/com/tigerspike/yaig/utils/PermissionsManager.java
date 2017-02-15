package com.tigerspike.yaig.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 10/04/16
 */
public class PermissionsManager {
    private Activity mActivity;

    public PermissionsManager(Activity activity) {
        mActivity = activity;
    }

    /**
     *
     */
    public boolean canIRun() {
        if (hasAllPermissions()) {
            return true;
        } else {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 2);
        }

        return false;
    }

    /**
     *
     *
     */
    public boolean hasAllPermissions() {
        return (hasPermission(mActivity, Manifest.permission.INTERNET) &&
                hasPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        );
    }

    private boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
