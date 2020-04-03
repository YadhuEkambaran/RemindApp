package com.aya.remindapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class RemindReceiver extends BroadcastReceiver {

    public static final String BUNDLE_TITLE_KEY = "title";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Please wash your hand";

        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                message = bundle.getString(BUNDLE_TITLE_KEY);
            }
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
