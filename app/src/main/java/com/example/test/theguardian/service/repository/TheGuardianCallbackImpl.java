package com.example.test.theguardian.service.repository;

import android.util.Log;

import com.example.test.theguardian.managers.ProgressDialogManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheGuardianCallbackImpl<RESPONSE> implements Callback<RESPONSE> {


    private static final String TAG = TheGuardianCallbackImpl.class.getSimpleName();

    private final TheGuardianServiceCallback<RESPONSE> webserviceCallback;
    private final Gson gson;

    TheGuardianCallbackImpl(final TheGuardianServiceCallback<RESPONSE> webserviceCallback) {
        this.webserviceCallback = webserviceCallback;
        this.gson = new Gson();
        try {
            ProgressDialogManager.getInstance(null).showLoadingDialog();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onResponse(final Call<RESPONSE> call, final Response<RESPONSE> response) {
        ProgressDialogManager.getInstance(null).closeProgressDialog();
        if (!response.isSuccessful()) {
            Log.e(TAG, "Response error: " + response.raw().message());
            ProgressDialogManager.getInstance(null).showAlertDialog(response.raw().message());
            webserviceCallback.onError(String.valueOf(response.raw().code()));
            return;
        }

        webserviceCallback.onResponse(response.body());
    }

    @Override
    public void onFailure(final Call<RESPONSE> call, final Throwable t) {
        Log.e(TAG, "Webservice failed.", t);
        webserviceCallback.onError(t.getLocalizedMessage());
    }

}
