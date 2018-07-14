package com.irshad.easylocation_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import com.irshad.EasyLocation.service.LocationService;

public class MainActivity extends AppCompatActivity {
    TextView tvAboutLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAboutLocation = (TextView) findViewById(R.id.tvAboutLocation);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService mLocationService = ((LocationService.LocationBinder) service).getService();
            mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener() {
                @Override
                public void getLocation(final String lastLatitude, final String lastLongitude, final String latitude, final String longitude, final String country, final String locality, final String street) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvAboutLocation.setText("lastLatitude: " + lastLatitude + "lastLongitude: " + lastLongitude + "latitude: " + latitude + "longitude: " + longitude + "getCountryName: " + country + "getLocality: " + locality + "getStreet: " + street
                            );
                        }
                    });
                }
            });
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, LocationService.class), conn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

}
