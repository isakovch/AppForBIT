package com.chyngyz.testapp.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferenceManager {
    private static final String STORAGE_TITLE = "com.testapp.prefs";
    private static final String NAME = "com.testapp.name";

    private SharedPreferences mPreferences;

    public PreferenceManager(Context context) {
        this.mPreferences = context.getSharedPreferences(STORAGE_TITLE, Context.MODE_PRIVATE);
    }

    public void saveData(String name) {
        mPreferences.edit().putString(NAME, name).apply();
    }

    public boolean isAuthenticated() {
        return !mPreferences.getString(NAME, "").isEmpty();
    }
}
