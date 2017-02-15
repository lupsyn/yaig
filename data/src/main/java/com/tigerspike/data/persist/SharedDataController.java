package com.tigerspike.data.persist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


/**
 * Copyright (c) 2016.  All rights reserved.
 *
 * @author enricodelzotto
 * @since 22/03/16
 */
public class SharedDataController implements ISharedDataController {
    private final static String LOG_TAG = SharedDataController.class.getSimpleName();
    public static final String PREFS_KEY = "FCpref";
    private SharedPreferences mSharedPreferences;

    public SharedDataController(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);

    }

    private synchronized <S> void addObject(String key, S object) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }


    private synchronized Object getObject(String key, Class object) {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(key, null);
        return gson.fromJson(json, object);
    }

    @Override
    public void save(String key, Object toSave) {
        try {
            addObject(key, toSave);
        } catch (Exception e) {

        }
    }

    @Override
    public <S> Object load(Class<S> classType, String key) {
        try {
            return getObject(key, classType);
        } catch (Exception e) {

            return null;
        }
    }

    public void deleteInternal(String key) {
        SharedPreferences.Editor prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString(key, null);
        prefsEditor.apply();
    }


}

