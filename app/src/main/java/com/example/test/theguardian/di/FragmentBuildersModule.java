package com.example.test.theguardian.di;

import com.example.test.theguardian.view.ui.NewsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract NewsListFragment contributeNewsListFragment();
}
