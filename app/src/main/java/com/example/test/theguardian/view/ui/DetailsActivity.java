package com.example.test.theguardian.view.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.theguardian.R;
import com.example.test.theguardian.databinding.DetailsActivityBinding;
import com.example.test.theguardian.viewmodel.NewsViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class DetailsActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    public static final String KEY_NEWS_TITLE = "news_title";
    public static final String KEY_NEWS_URL = "news_url";

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    DetailsActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.details_activity);


        String array = getIntent().getStringExtra("url");

        final NewsViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewsViewModel.class);

        viewModel.setNewsTitle(getIntent().getStringExtra(KEY_NEWS_TITLE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.ll.setTransitionName(getString(R.string.transition_name));
        }
        binding.setNewsViewModel(viewModel);
        binding.setIsLoading(true);

        binding.wvNews.loadUrl(getIntent().getStringExtra(KEY_NEWS_URL));
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}



