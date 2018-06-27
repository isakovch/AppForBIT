package com.chyngyz.testapp.di.list;

import com.chyngyz.testapp.ui.list.DataListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = ListModule.class)
@ListScope
public interface ListComponent {
    void inject(DataListFragment fragment);
}

