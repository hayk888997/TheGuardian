package com.example.test.theguardian.view.callback;

import android.view.View;

import com.example.test.theguardian.service.model.NewsModel;

public interface NewsClickCallback {
    void onClick(NewsModel newsModel, View sharedElement);
}
