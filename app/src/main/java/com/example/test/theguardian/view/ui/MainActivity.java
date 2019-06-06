package com.example.test.theguardian.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.test.theguardian.R;
import com.example.test.theguardian.managers.ProgressDialogManager;
import com.example.test.theguardian.service.model.NewsModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    NewsListFragment fragment;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init ProgressDialogManager
        ProgressDialogManager.getInstance(this);

        // Add news list fragment if this is first creation
        if (savedInstanceState == null) {
            fragment = new NewsListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, NewsListFragment.TAG).commit();
        }
    }

    /**
     * Shows the news detail activity
     */
    public void show(NewsModel news, View sharedElement) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

        intent.putExtra(DetailsActivity.KEY_NEWS_TITLE, news.getWebTitle());
        intent.putExtra(DetailsActivity.KEY_NEWS_URL, news.getWebUrl());
        String transitionName = getString(R.string.transition_name);

        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                        sharedElement, transitionName);

        ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());

    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
