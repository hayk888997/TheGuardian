package com.example.test.theguardian.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.test.theguardian.service.dto.GetNewsResponse;
import com.example.test.theguardian.service.repository.TheGuardianRepo;

import javax.inject.Inject;

public class NewsListViewModel extends AndroidViewModel {

    private TheGuardianRepo mTheGuardianRepo;

    @Inject
    public NewsListViewModel(@NonNull TheGuardianRepo theGuardianRepo, @NonNull Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        mTheGuardianRepo = theGuardianRepo;
        LiveData<GetNewsResponse> newsResponseObservable = theGuardianRepo.getNewsResponse();
    }

    /**
     * Expose the LiveData News query so the UI can observe it.
     */
    public LiveData<GetNewsResponse> getNewsResponseObservable() {
            return mTheGuardianRepo.getNewsResponse();
    }
}
