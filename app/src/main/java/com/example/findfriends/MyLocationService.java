package com.example.findfriends;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.telephony.SmsManager;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MyLocationService extends Service {
    public String numero;
    public MyLocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //recuperation position GPS
        numero=intent.getStringExtra("numero");
        FusedLocationProviderClient mClient= LocationServices.getFusedLocationProviderClient(this);
        mClient.getLastLocation().addOnSuccessListener((location -> {
            sendSms(location);
        }));
        LocationRequest request=LocationRequest.create().setSmallestDisplacement(100).setFastestInterval(60000);
        LocationCallback callback=new LocationCallback() {


            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location= locationResult.getLastLocation();
                sendSms(location);
            }
        };
        mClient.requestLocationUpdates(request,callback,null);
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendSms(Location location) {
        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(numero,
                null,
                "findfriemds:ma position est:#"+location.getLongitude()+"#"+location.getLatitude(),
                null,
                null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}