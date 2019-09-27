package com.example.broadcastestatico;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.telecom.Call;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        Data.Builder data1 = new Data.Builder();
        data1.putString("number","994595937");

        Data.Builder data2 = new Data.Builder();
        data2.putString("number","999999222");

        OneTimeWorkRequest compressionWork1 =
                new OneTimeWorkRequest.Builder(CallWorker.class)
                        .setConstraints(constraints)
                        .setInputData(data1.build())
                        .setInitialDelay(15,TimeUnit.SECONDS)
                        .build();

        WorkManager.getInstance(this)
                .enqueue(compressionWork1);

        OneTimeWorkRequest compressionWork2 =
                new OneTimeWorkRequest.Builder(CallWorker.class)
                        .setConstraints(constraints)
                        .setInputData(data2.build())
                        .setInitialDelay(75,TimeUnit.SECONDS)
                        .build();

        WorkManager.getInstance(this)
                .enqueue(compressionWork2);
    }
}
