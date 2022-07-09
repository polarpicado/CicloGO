package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapityActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    float latitud, longitud;
    String titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapity);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        recuperarDatos();
        LatLng bici= new LatLng(latitud,longitud);
        mMap.addMarker(new MarkerOptions().position(bici).title(titulo));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bici,16));
    }

    private void recuperarDatos(){
        latitud=Float.parseFloat(getIntent().getStringExtra("latitud"));
        longitud=Float.parseFloat(getIntent().getStringExtra("longitud"));
        titulo=getIntent().getStringExtra("titulo");

    }
}