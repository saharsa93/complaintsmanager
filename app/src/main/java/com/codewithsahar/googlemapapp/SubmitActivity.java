package com.codewithsahar.googlemapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

public class SubmitActivity extends AppCompatActivity implements LocationListener {

    TextView phone_sub, phone_tv, signaltype_tv, signaltype_sub;
    TextView strength_tv, strength_sub, latitude_tv, latitude_sub, longitude_tv, longitude_sub;
    EditText description_et;
    Button Submit;

    myPhoneStateListener psListener;
    Location location;
    private ProgressBar progress;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        phone_sub = findViewById(R.id.phone_sub);
        phone_tv = findViewById(R.id.id_tv);
        signaltype_tv = findViewById(R.id.signal_type);
        signaltype_sub = findViewById(R.id.signal_sub);
        strength_tv = findViewById(R.id.signal_strength);
        strength_sub = findViewById(R.id.strength_sub);
        description_et = findViewById(R.id.details_et);
        Submit = findViewById(R.id.submit_btn);
        latitude_tv = findViewById(R.id.lat_tv);
        latitude_sub = findViewById(R.id.lat_sub);
        longitude_tv = findViewById(R.id.lon_tv);
        longitude_sub = findViewById(R.id.lon_sub);
        progress = findViewById(R.id.progress);


        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        ActivityCompat.requestPermissions(this,new String[]{READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String phoneNumber1 = manager.getLine1Number();
        phone_tv.setText( phoneNumber1);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickkk();
                Intent intent = new Intent(SubmitActivity.this, DoneActivity.class);
                startActivity(intent);
            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            switch (manager.getDataNetworkType()) {
                case NETWORK_TYPE_EDGE:
                case NETWORK_TYPE_GPRS:
                case NETWORK_TYPE_CDMA:
                case NETWORK_TYPE_IDEN:
                case NETWORK_TYPE_1xRTT:
                    signaltype_tv.setText("2G");
                    break;
                case NETWORK_TYPE_UMTS:
                case NETWORK_TYPE_HSDPA:
                case NETWORK_TYPE_HSPA:
                case NETWORK_TYPE_HSPAP:
                case NETWORK_TYPE_EVDO_0:
                case NETWORK_TYPE_EVDO_A:
                case NETWORK_TYPE_EVDO_B:
                    signaltype_tv.setText("3G");
                    break;
                case NETWORK_TYPE_LTE:
                    signaltype_tv.setText("4G");
                    break;
                case NETWORK_TYPE_NR:
                    signaltype_tv.setText("5G");
                    break;
                default:
                    signaltype_tv.setText("Unknown");
            }
        }

        psListener = new myPhoneStateListener();
        manager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(psListener, myPhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();

        String provider = lm.getBestProvider(c, false);

        Location l = lm.getLastKnownLocation(provider);

        if (l != null) {     //get latitude and longitude of the location
            double lng = l.getLongitude();
            double lat = l.getLatitude();
            //display on text view
            longitude_tv.setText("" + lng);
            latitude_tv.setText("" + lat);
        } else {
            longitude_tv.setText("No Provider");
            latitude_tv.setText("No Provider");
        }

        lm.requestLocationUpdates(provider, 20000, 0, this);

    }

    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        double lng = location.getLongitude();
        double lat = location.getLatitude();
        //display on text view
        longitude_tv.setText("" + lng);
        latitude_tv.setText("" + lat);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


    public class myPhoneStateListener extends PhoneStateListener {
        public int signalStrengthValue;

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (signalStrength.isGsm()) {
                if (signalStrength.getGsmSignalStrength() != 99)
                    signalStrengthValue = signalStrength.getGsmSignalStrength() * 2 - 113;
                else
                    signalStrengthValue = signalStrength.getGsmSignalStrength();
            } else {
                signalStrengthValue = signalStrength.getCdmaDbm();
            }
            strength_tv.setText("" + signalStrengthValue);
        }
    }

    public void Register(String Type, String phone, String signaltype, String phsignalstrength,
                         String latitude,String longitude,String description) throws Exception{
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sabeti.hamidrahimiam.ir/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
            Call<ResponseBody> call = registerInterface.register(Type, phone, signaltype, phsignalstrength, latitude, longitude, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Snackbar.make(coordinatorLayout, "onResponse" + response.body().string(), Snackbar.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SubmitActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });


    }

    public void onClickkk() {
        String type = "signup";
        String phone = phone_tv.getText().toString();
        String signaltype = signaltype_tv.getText().toString();
        String signalstrength = strength_tv.getText().toString();
        String latitude = latitude_tv.getText().toString();
        String longitude = longitude_tv.getText().toString();
        String description = description_et.getText().toString();

        if(!description.isEmpty()) {
            progress.setVisibility(View.VISIBLE);
            try {
                Register(type, phone, signaltype, signalstrength, latitude, longitude, description);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        }

    }

}


