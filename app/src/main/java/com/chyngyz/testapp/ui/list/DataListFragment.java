package com.chyngyz.testapp.ui.list;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.TestApplication;
import com.chyngyz.testapp.data.entity.Car;
import com.chyngyz.testapp.data.entity.User;
import com.chyngyz.testapp.di.list.ListModule;
import com.chyngyz.testapp.ui.BaseFragment;
import com.chyngyz.testapp.utils.AppConstants;
import com.chyngyz.testapp.utils.ApplicationUtils;
import com.chyngyz.testapp.utils.MsgUtils;
import com.chyngyz.testapp.utils.PermissionUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.chyngyz.testapp.utils.AppConstants.REQUEST_CODE_STORAGE_PERMISSION;

public class DataListFragment extends BaseFragment implements DataListContract.View, View.OnClickListener {
    @BindView(R.id.image_view)
    ImageView mImageView;
    @BindView(R.id.list_view)
    ListView mListView;

    @Inject
    DataListContract.Presenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_data_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependency();
        initPresenter();
    }

    private void initDependency() {
        TestApplication
                .getComponent()
                .include(new ListModule())
                .inject(this);
    }

    private void initPresenter() {
        mPresenter.bind(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.fetchUserInfo();
        }
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();
    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }

    @Override
    public void showUsersList(User user, Car car) {
        mListView.setAdapter(new DataListAdapter(user.getDataList(), car.getDataList()));
    }

    @Override
    public void showError(String error) {
        MsgUtils.showLongToast(getContext(), error);
    }

    @OnClick(R.id.btn_image)
    @Override
    public void onClick(View v) {
        if (PermissionUtils.checkStoragePermission(this, getContext())) {
            startGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startGallery();
            }
        }
    }

    private void startGallery() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AppConstants.REQUEST_CODE_GALLERY_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == AppConstants.REQUEST_CODE_GALLERY_IMAGE) {
            ApplicationUtils.setImageFromGallery(mImageView, requireActivity(), data.getData());
        }
    }
}
