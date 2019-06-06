package com.example.test.theguardian.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.theguardian.managers.ProgressDialogManager;
import com.example.test.theguardian.service.dto.GetNewsResponse;
import com.example.test.theguardian.service.dto.MainResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TheGuardianRepo {
    private TheGuardianService theGuardianService;

    @Inject
    public TheGuardianRepo(TheGuardianService theGuardianService) {
        this.theGuardianService = theGuardianService;
    }

    public LiveData<GetNewsResponse> getNewsResponse() {
        MutableLiveData<GetNewsResponse> data = new MutableLiveData<>();
        theGuardianService.getNewsList().enqueue(new TheGuardianCallbackImpl<>(new TheGuardianServiceCallback<MainResponse>() {
            @Override
            public void onResponse(MainResponse mainResponse) {
                data.setValue(mainResponse.getNewsResponse());
            }

            @Override
            public void onError(String errorStatus) {
                try {
                    ProgressDialogManager.getInstance(null).showAlertDialog(errorStatus);
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }));

        return data;
    }

}
