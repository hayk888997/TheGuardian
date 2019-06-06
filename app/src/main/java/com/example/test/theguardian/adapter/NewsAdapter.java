package com.example.test.theguardian.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.theguardian.R;
import com.example.test.theguardian.databinding.NewsListItemBinding;
import com.example.test.theguardian.service.model.NewsModel;
import com.example.test.theguardian.view.callback.NewsClickCallback;

import java.util.List;
import java.util.Objects;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<NewsModel> newsList;

    @Nullable
    private final NewsClickCallback newsClickCallback;

    public NewsAdapter(@Nullable NewsClickCallback newsClickCallback) {
        this.newsClickCallback = newsClickCallback;
    }

    public void setNewsList(final List<NewsModel> newsList) {
        if (this.newsList == null) {
            this.newsList = newsList;
            notifyItemRangeInserted(0, 0);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return NewsAdapter.this.newsList.size();
                }

                @Override
                public int getNewListSize() {
                    return newsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return NewsAdapter.this.newsList.get(oldItemPosition).getId().equals(
                            newsList.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NewsModel news = newsList.get(newItemPosition);
                    NewsModel old = newsList.get(oldItemPosition);
                    return news.getId().equals(old.getId())
                            && Objects.equals(news.getApiUrl(), old.getApiUrl());
                }
            });
            this.newsList = newsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.news_list_item,
                        parent, false);

        binding.setCallback(newsClickCallback);

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.binding.setNews(newsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        final NewsListItemBinding binding;

        public NewsViewHolder(NewsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
