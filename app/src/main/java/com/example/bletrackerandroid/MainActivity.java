package com.example.bletrackerandroid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

    private BottomNavigationView bottomNavigationView;

    private static final String TAG = "MainActivity";

    private BeaconManager beaconManager = null;
    private Region beaconRegion = null;

    private static final String ALTBEACON_LAYOUT = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";

    private void showAlert(final String title, final String message) {
        runOnUiThread(() -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1234);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(ALTBEACON_LAYOUT));
        beaconManager.bind(this);

        //Home Page ListAll
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ListAllFragment()).commit();

    }

    private Boolean entryMessageRaised = false;
    private Boolean exitMessageRaised = false;
    private Boolean rangingMessageRaised = false;

    @Override
    public void onBeaconServiceConnect() {
        Log.d(TAG, "onBeaconServiceConnect called");

        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                if (!entryMessageRaised) {
                    showAlert("didEnterRegion", "Entering region " + region.getUniqueId() +
                            " Beacon detected UUID/major/minor: " + region.getId1() +
                            "/" + region.getId2() + "/" + region.getId3());
                    entryMessageRaised = true;
                }
            }

            @Override
            public void didExitRegion(Region region) {
                if (!exitMessageRaised) {
                    showAlert("didExitRegion", "Exiting region " + region.getUniqueId() +
                            " Beacon detected UUID/major/minor: " + region.getId1() +
                            "/" + region.getId2() + "/" + region.getId3());
                    exitMessageRaised = true;
                }
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                // N/A
            }
        });

        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (!rangingMessageRaised && beacons != null && !beacons.isEmpty()) {
                    for (Beacon beacon: beacons) {
                        showAlert("didExitRegion", "Ranging region " + region.getUniqueId() +
                                " Beacon detected UUID/major/minor: " + beacon.getId1() +
                                "/" + beacon.getId2() + "/" + beacon.getId3());
                        rangingMessageRaised = true;
                    }
                }
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    androidx.fragment.app.Fragment fragment=null;

                    switch(menuItem.getItemId()) {
                        case R.id.listAll:
                            fragment=new ListAllFragment();
                            break;
                        case R.id.listNearby:
                            fragment=new ListNearbyFragment();
                            break;
                        case R.id.mapNearby:
                            fragment=new MapNearbyFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }
            };
}