package com.example.test.theguardian.di;

import com.example.test.theguardian.viewmodel.NewsListViewModel;
import com.example.test.theguardian.viewmodel.NewsViewModel;
import com.example.test.theguardian.viewmodel.TheGuardianViewModelFactory;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link TheGuardianViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    NewsListViewModel newsListViewModel();
    NewsViewModel newsViewModel();
}
