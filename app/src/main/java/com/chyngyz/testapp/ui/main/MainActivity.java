package com.chyngyz.testapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.chyngyz.testapp.R;
import com.chyngyz.testapp.ui.BaseActivity;
import com.chyngyz.testapp.ui.list.DataListFragment;
import com.chyngyz.testapp.ui.maps.MapsFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapsFragment(), getString(R.string.title_maps));
        adapter.addFragment(new DataListFragment(), getString(R.string.title_list));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.title_main);
    }
}
