package com.chyngyz.testapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.utils.MsgUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;

    protected abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    protected void dismissProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected void showMessage(String msg) {
        MsgUtils.showShortToast(getContext(), msg);
    }

    protected void showMessage(int resId) {
        MsgUtils.showShortToast(getContext(), getString(resId));
    }

    protected void showServicesDisabledDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.title_location_disabled)
                .setMessage(R.string.msg_location_settings_not_enabled)
                .setCancelable(false)
                .setPositiveButton(R.string.action_go_to_settings, (dialogInterface, which) -> {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    dialogInterface.dismiss();
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })
                .create()
                .show();
    }
}
