package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaRutaActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap2;
    float latitud_inicial, longitud_inicial, latitud_final, longitud_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ruta);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa2);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap2 = googleMap;
        mMap2.getUiSettings().setZoomControlsEnabled(true);

        recuperarDatos();

        LatLng pos1 = new LatLng(latitud_inicial, longitud_inicial);
        LatLng pos2 = new LatLng(latitud_final, longitud_final);
        mMap2.addMarker(new MarkerOptions().position(pos1).title("Punto 1"));
        mMap2.addMarker(new MarkerOptions().position(pos2).title("Punto 2"));
        mMap2.animateCamera(CameraUpdateFactory.newLatLngZoom(pos1,14));

    }

    private void recuperarDatos() {
        latitud_inicial = Float.parseFloat(getIntent().getStringExtra("platitudinicio"));
        longitud_inicial = Float.parseFloat(getIntent().getStringExtra("plongitudinicio"));
        latitud_final = Float.parseFloat(getIntent().getStringExtra("platitudfinal"));
        longitud_final = Float.parseFloat(getIntent().getStringExtra("plongitudfinal"));
    }
}