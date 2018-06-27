package com.chyngyz.testapp.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

public final class PermissionUtils {

    private static String[] sLocationPermissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static String[] sStoragePermissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static boolean checkLocationServices(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null &&
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static boolean checkLocationPermissions(Fragment fragment, Context context) {
        if (!permissionsGranted(context, sLocationPermissions)) {
            fragment.requestPermissions(sLocationPermissions, AppConstants.REQUEST_CODE_LOCATION_PERMISSION);
            return false;
        }
        return true;
    }

    public static boolean checkStoragePermission(Fragment fragment, Context context) {
        if (!permissionsGranted(context, sStoragePermissions)) {
            fragment.requestPermissions(sStoragePermissions, AppConstants.REQUEST_CODE_STORAGE_PERMISSION);
            return false;
        }
        return true;
    }

    private static boolean permissionsGranted(Context context, String[] permissions) {
        boolean granted = false;

        for (String permission : permissions) {
            granted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            if (!granted) break;
        }
        return granted;
    }
}
