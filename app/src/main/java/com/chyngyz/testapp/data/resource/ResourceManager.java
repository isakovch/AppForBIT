package com.chyngyz.testapp.data.resource;

import android.content.Context;

public class ResourceManager {

    private Context mContext;

    public ResourceManager(Context context) {
        this.mContext = context;
    }

    public String getStringResource(int resId) {
        return mContext.getString(resId);
    }
}
