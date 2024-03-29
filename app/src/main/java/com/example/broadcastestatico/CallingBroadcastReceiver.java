package com.example.broadcastestatico;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallingBroadcastReceiver extends BroadcastReceiver {

    private final String PHONE_RECEIVE_CALL_1 = "994595937";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        boolean isCharging = (action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED));
        String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        llamadaEntrante(isCharging, phoneNumber,context);
    }

    private void llamadaEntrante(boolean callIn, String phoneNumber, Context context) {
        if (callIn){
            Toast.makeText(context, "Llamada entrante: " + phoneNumber, Toast.LENGTH_SHORT).show();

            TelecomManager tm = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
            Log.i("RICHI/CALL","excepcion de llamada");
            if (tm == null) {
                // whether you want to handle this is up to you really
//                throw new NullPointerException("tm == null");
                Log.i("RICHI-CALLIN:","excepcion de llamada");
            }

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return;
            }

            if (phoneNumber.equals(PHONE_RECEIVE_CALL_1)) tm.acceptRingingCall();
            else tm.endCall();
        }
    }
}
