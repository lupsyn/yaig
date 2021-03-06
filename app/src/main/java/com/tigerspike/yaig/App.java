package com.tigerspike.yaig;

import android.app.Application;

import com.tigerspike.yaig.utils.PermissionsManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
