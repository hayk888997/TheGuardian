package com.example.test.theguardian.di;

import com.example.test.theguardian.view.ui.DetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailsActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract DetailsActivity contributeDetailsActivity();
}
