package com.example.test.theguardian.utils;

import android.app.Activity;
import android.app.ProgressDialog;


public class CustomProgressDialog {
    ProgressDialog progressDialog;
    private Activity mActivity;

    public CustomProgressDialog(Activity activity) {
        mActivity = activity;

        progressDialog = new ProgressDialog(activity);
        if (mActivity != null
                && !mActivity.isFinishing()) {
            progressDialog.setMessage("In Progress ...");
        }
    }

    public void show() {
        if (progressDialog != null
                && mActivity != null
                && !mActivity.isFinishing()) {
            progressDialog.show();
        }
    }

    public void close() {
        if (progressDialog != null
                && mActivity != null
                && !mActivity.isFinishing()) {
            progressDialog.cancel();
        }
    }
}
