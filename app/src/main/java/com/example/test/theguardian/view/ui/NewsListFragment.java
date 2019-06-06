package com.example.test.theguardian.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.theguardian.R;
import com.example.test.theguardian.adapter.NewsAdapter;
import com.example.test.theguardian.databinding.FragmentNewsListBinding;
import com.example.test.theguardian.di.Injectable;
import com.example.test.theguardian.view.callback.NewsClickCallback;
import com.example.test.theguardian.viewmodel.NewsListViewModel;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class NewsListFragment extends Fragment implements Injectable {
    static final String TAG = "NewsListFragment";
    private NewsAdapter newsAdapter;
    private FragmentNewsListBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);

        newsAdapter = new NewsAdapter(newsClickCallback);
        binding.rvNewsList.setAdapter(newsAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final NewsListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(NewsListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(NewsListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getNewsResponseObservable().observe(this, getNewsResponse -> {
            if(getNewsResponse != null) {
                binding.setIsLoading(false);
                newsAdapter.setNewsList(getNewsResponse.getNewsList());
            }
        });
    }

    private final NewsClickCallback newsClickCallback = (news, sharedElement) -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) Objects.requireNonNull(getActivity())).show(news, sharedElement);
        }
    };
}
