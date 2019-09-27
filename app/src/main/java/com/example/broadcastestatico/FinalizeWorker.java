package com.example.broadcastestatico;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.lang.reflect.Method;

public class FinalizeWorker extends Worker {

    Context context;

    public FinalizeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        TelecomManager tm = (TelecomManager)context.getSystemService(Context.TELECOM_SERVICE);

        if (tm == null) {
            // whether you want to handle this is up to you really
            throw new NullPointerException("tm == null");
        }

        if (context.checkSelfPermission(Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return null;
        }
        tm.endCall();
        return Result.success();
    }
}
