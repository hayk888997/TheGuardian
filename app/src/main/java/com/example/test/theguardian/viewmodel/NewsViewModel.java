package com.example.test.theguardian.viewmodel;

import android.app.Application;

import com.example.test.theguardian.service.model.NewsModel;
import com.example.test.theguardian.service.repository.TheGuardianRepo;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class NewsViewModel extends AndroidViewModel {
    private static final String TAG = NewsViewModel.class.getName();

    public final MutableLiveData<String> newsTitle;

    public ObservableField<NewsModel> news = new ObservableField<>();

    @Inject
    public NewsViewModel(@NonNull TheGuardianRepo theGuardianRepo, @NonNull Application application) {
        super(application);

        this.newsTitle = new MutableLiveData<>();
    }

    public void setNews(NewsModel newsModel) {
        this.news.set(newsModel);
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle.setValue(newsTitle);
    }
}
