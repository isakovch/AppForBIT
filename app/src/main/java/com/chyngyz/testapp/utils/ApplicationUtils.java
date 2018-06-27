package com.chyngyz.testapp.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;

public final class ApplicationUtils {

    public static void setImageFromGallery(ImageView imageView, Activity activity, Uri uri) {
        Bitmap bitmapImage = null;
        try {
            bitmapImage = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmapImage);
    }

}
