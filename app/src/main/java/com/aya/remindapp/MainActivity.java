package com.aya.remindapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ALARM_REQUEST_CODE = 1000;

    private String mTitle = "Please wash your hands";

    private long mInterval = 5000;

    private AppCompatEditText edtTitle;
    private AppCompatButton btnHalfHour;
    private AppCompatButton btnOneHour;
    private AppCompatButton btnTwoHours;
    private AppCompatButton btnFourHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edt_main_title);
        btnHalfHour = findViewById(R.id.btn_main_half);
        btnOneHour = findViewById(R.id.btn_main_one);
        btnTwoHours = findViewById(R.id.btn_main_two);
        btnFourHours = findViewById(R.id.btn_main_four);
        AppCompatButton btnSubmit = findViewById(R.id.btn_main_sumbit);

        btnHalfHour.setOnClickListener(this);
        btnOneHour.setOnClickListener(this);
        btnTwoHours.setOnClickListener(this);
        btnFourHours.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnHalfHour) {
            mInterval = 60 * 30 * 1000;
        } else if (view == btnOneHour) {
            mInterval = 60 * 60 * 1000;
        } else if (view == btnTwoHours) {
            mInterval = 2 * (60 * 30 * 1000);
        } else if (view == btnFourHours) {
            mInterval = 4 * (60 * 30 * 1000);
        } else {
            String title = edtTitle.getText().toString().trim();
            if (title.length() != 0) {
                mTitle = title;
            }

            setAlarmManager();
        }
    }

    private void setAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent remindBroadcastIntent = new Intent(MainActivity.this, RemindReceiver.class);
        remindBroadcastIntent.putExtra(RemindReceiver.BUNDLE_TITLE_KEY, mTitle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQUEST_CODE, remindBroadcastIntent, 0);
        
        if (manager == null) {
            Toast.makeText(this, "Alarm not inited", Toast.LENGTH_SHORT).show();
            return;
        }

        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + mInterval,
                mInterval, pendingIntent);
    }
}
