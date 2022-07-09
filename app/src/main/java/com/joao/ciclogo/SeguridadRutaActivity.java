package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joao.ciclogo.entidad.Rutas;

import java.util.HashMap;
import java.util.UUID;

public class SeguridadRutaActivity extends AppCompatActivity {
    RadioGroup rd_segruta;
    RadioButton rb_segura, rb_regular, rb_peligrosa;
    EditText txt_nomruta;
    Button btn_crearruta;
    Rutas ruta;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;
    HashMap map = new HashMap();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_ruta);
        mAuth = FirebaseAuth.getInstance();
        asignarReferencias();
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void asignarReferencias(){
        rd_segruta = findViewById(R.id.rd_segruta);
        rb_segura = findViewById(R.id.rb_segura);
        rb_regular = findViewById(R.id.rb_regular);
        rb_peligrosa = findViewById(R.id.rb_peligrosa);
        txt_nomruta = findViewById(R.id.txt_nomruta);
        btn_crearruta = findViewById(R.id.btn_crearruta);
        btn_crearruta.setOnClickListener(v -> {
            capturarDatos();
            String mensaje = "";
            databaseReference.child("Rutas").child(ruta.getId()).setValue(ruta);
            mensaje = "Ruta creada correctamente";
            AlertDialog.Builder builder = new AlertDialog.Builder(SeguridadRutaActivity.this);
            builder.setTitle("Mensaje");
            builder.setMessage(mensaje);
            builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {
                Intent intent = new Intent(SeguridadRutaActivity.this,menuActivity.class);
                startActivity(intent);
            });
            builder.create().show();
        });
    }
    private void capturarDatos(){
        String creador= mAuth.getCurrentUser().getEmail().toString();
        String nombre = txt_nomruta.getText().toString();
        String peligrosidad = rb_segura.isChecked() ? "Segura" : rb_regular.isChecked() ? "Regular" : "Peligrosa";
        String lon_ini = getIntent().getStringExtra("plongitudinicial");
        String lat_ini = getIntent().getStringExtra("platitudinicial");
        String lon_fin = getIntent().getStringExtra("plongitudfinal");
        String lat_fin = getIntent().getStringExtra("platitudfinal");
        ruta = new Rutas(UUID.randomUUID().toString(), creador, nombre, lat_ini,lon_ini, lat_fin, lon_fin, peligrosidad);
    }
}