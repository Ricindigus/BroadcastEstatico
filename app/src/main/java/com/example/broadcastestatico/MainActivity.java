package com.example.broadcastestatico;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.telecom.Call;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ANSWER_PHONE_CALLS};
                requestPermissions(permissions, 1);
            }
        }

//        OneTimeWorkRequest compressionWork2 =
//                new OneTimeWorkRequest.Builder(CallWorker.class)
//                        .setConstraints(constraints)
//                        .setInputData(data2.build())
//                        .setInitialDelay(75,TimeUnit.SECONDS)
//                        .build();
//
//        WorkManager.getInstance(this)
//                .enqueue(compressionWork2);
    }

    public void iniciarTarea(View view) {


        Constraints constraints = new Constraints.Builder()
                .build();

        Data.Builder data1 = new Data.Builder();
        data1.putString("number","994595937");
        data1.putInt("duration",1);

        Data.Builder data2 = new Data.Builder();
        data2.putString("number","999999222");
        data2.putInt("duration",1);

        OneTimeWorkRequest compressionWork1 =
                new OneTimeWorkRequest.Builder(CallWorker.class)
                        .setConstraints(constraints)
                        .setInputData(data1.build())
                        .build();

        WorkManager.getInstance(this)
                .enqueue(compressionWork1);


//        OneTimeWorkRequest finalizeWork =
//                new OneTimeWorkRequest.Builder(FinalizeWorker.class)
//                        .setInitialDelay(15,TimeUnit.SECONDS)
//                        .build();
//
//        WorkManager.getInstance(this)
//                .enqueue(finalizeWork);

//        OneTimeWorkRequest compressionWork2 =
//                new OneTimeWorkRequest.Builder(CallWorker.class)
//                        .setConstraints(constraints)
//                        .setInitialDelay(10,TimeUnit.SECONDS)
//                        .setInputData(data2.build())
//                        .build();
//
//        WorkManager.getInstance(this)
//                .enqueue(compressionWork2);
    }
}
