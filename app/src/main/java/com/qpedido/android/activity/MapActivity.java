package com.qpedido.android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.ui.IconGenerator;
import com.qpedido.android.R;
import com.qpedido.android.constant.Constant;
import com.qpedido.android.model.Restaurant;
import org.jetbrains.annotations.NotNull;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback  {
    private GoogleMap googleMap;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 8);

        LocationServices.getFusedLocationProviderClient(getApplicationContext()).getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null && googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12f));
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        for (Restaurant restaurant: Constant.PARTNERS) {
            Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(restaurant.getLatitude(), restaurant.getLongitude())));
            if (marker != null) {
                IconGenerator iconFactory = new IconGenerator(this);
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon( restaurant.getIcon() + " " + restaurant.getName())));
            }
        }
    }

    public void onClickReturn(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}