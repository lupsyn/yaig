package com.tigerspike.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tigerspike.business.controller.sharing.ISaveController;
import com.tigerspike.business.controller.sharing.ISharedCallback;
import com.tigerspike.business.controller.sharing.ISharingController;
import com.tigerspike.business.entity.FlickrImage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 15/02/2017
 */
public class SharingDataController implements ISharingController {
    private Context mContext;


    public SharingDataController(Context context) {
        mContext = context;
    }

    @Override
    public void saveImage(final FlickrImage image, final ISaveController callback) {

        Picasso.with(mContext).load(image.getLink()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {


                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/" + image.getTitle() + ".png");
                    file.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    callback.onSuccess(Environment.DIRECTORY_PICTURES + "/" +
                            ((image.getTitle() == null || image.getTitle().trim().isEmpty()) ? UUID.randomUUID().toString() : image.getTitle().trim())
                            + ".png");
                } catch (Exception e) {
                    callback.onError(e.getMessage().toString());
                    e.printStackTrace();
                }


            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }

    @Override
    public void shareImage(final FlickrImage image, final ISharedCallback callback) {


        Picasso.with(mContext).load(image.getLink()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                try {
                    File file = new File(mContext.getExternalCacheDir(), image.getTitle() + ".png");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    callback.onSuccess();
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    intent.setType("image/png");
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    callback.onError(e.getMessage().toString());
                    e.printStackTrace();
                }


            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }


    @Override
    public void openImage(FlickrImage image) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getExternalLink()));
        Intent chooser = Intent.createChooser(sendIntent, "Choose Your Browser");
        if (sendIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(chooser);
        }
    }
}
