package com.chyngyz.testapp.utils;

import android.content.Context;
import android.widget.Toast;

public final class MsgUtils {

    public static void showShortToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }

}
