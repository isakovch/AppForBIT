package com.chyngyz.testapp.ui.maps;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.ui.BaseFragment;
import com.chyngyz.testapp.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;

import static com.chyngyz.testapp.utils.AppConstants.REQUEST_CODE_LOCATION_PERMISSION;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final float DEFAULT_ZOOM_VALUE = 14;

    @BindView(R.id.map_view)
    MapView mMapView;

    private GoogleMap mGoogleMap;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location mLastKnownLocation;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_maps;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initMapView(savedInstanceState);
        initLocationProvider();
    }

    private void initMapView(Bundle bundle) {
        mMapView.onCreate(bundle);

        if (mMapView != null) {
            mMapView.getMapAsync(this);
        }
    }

    private void initLocationProvider() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        checkLocationServices();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setOnMarkerClickListener(this);

        enableMyLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            }
        }
    }

    private void checkLocationServices() {
        if (!PermissionUtils.checkLocationServices(requireContext())) {
            showServicesDisabledDialog();
        }
    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        if (PermissionUtils.checkLocationPermissions(this, requireContext())) {
            Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(requireActivity(), task -> {
                if (task.getResult() != null) {
                    mLastKnownLocation = task.getResult();
                    showLocationOnMap(new LatLng(
                            mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()));
                    return;
                }

                showMessage(R.string.msg_location_not_available);
            });
        }
    }

    private void showLocationOnMap(LatLng currentPosition) {
        mGoogleMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .title(String.format("Coordinates: %1s, %2s", currentPosition.latitude, currentPosition.longitude))
        );

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(
                currentPosition,
                DEFAULT_ZOOM_VALUE);

        mGoogleMap.moveCamera(cu);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }
}
