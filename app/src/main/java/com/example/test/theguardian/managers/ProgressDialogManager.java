package com.example.test.theguardian.managers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.test.theguardian.R;
import com.example.test.theguardian.utils.CustomProgressDialog;

public class ProgressDialogManager {

    private static ProgressDialogManager manager = null;

    private static Activity activity;

    private static AlertDialog alertDialog = null;
    private static CustomProgressDialog progressDialog = null;

    private ProgressDialogManager(Activity activity) {
        this.activity = activity;

    }

    public static ProgressDialogManager getInstance(Activity activity) {
        if(activity != null) {
            manager = new ProgressDialogManager(activity);
            progressDialog = null;
        }
        return manager;
    }

    public void showAlertDialog(String msg) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(activity).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage(msg);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity.getString(R.string.ok),
                    (dialog, which) -> {
                        dialog.dismiss();
                        progressDialog.close();
                    });
        }

        alertDialog.show();
    }

    public void closeAlertDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new CustomProgressDialog(activity);
        }
        progressDialog.show();
    }

    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.close();
        }
    }
}
