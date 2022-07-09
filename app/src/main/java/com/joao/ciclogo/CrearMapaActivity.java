package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class CrearMapaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    EditText txtCrearLongitudInicial, txtCrearLatitudInicial, txtCrearLongitudFinal, txtCrearLatitudFinal;
    Button btnCrear;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mapa);
        asignarReferencias();
        btnCrear.setOnClickListener(view -> {
            if(validacion()){
                Intent intent = new Intent(CrearMapaActivity.this, SeguridadRutaActivity.class);
                intent.putExtra("plongitudinicial",txtCrearLongitudInicial.getText().toString()+"");
                intent.putExtra("plongitudfinal",txtCrearLongitudFinal.getText().toString()+"");
                intent.putExtra("platitudinicial",txtCrearLatitudInicial.getText().toString()+"");
                intent.putExtra("platitudfinal",txtCrearLatitudFinal.getText().toString()+"");
                startActivity(intent);
            }else{
                Toast.makeText(CrearMapaActivity.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            });
        setToolBar();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navview);
        toolbar=findViewById(R.id.toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
    }
    private void asignarReferencias(){
        txtCrearLongitudInicial = findViewById(R.id.txtCrearLongitudInicial);
        txtCrearLatitudInicial = findViewById(R.id.txtCrearLatitudInicial);
        txtCrearLongitudFinal = findViewById(R.id.txtCrearLongitudFinal);
        txtCrearLatitudFinal = findViewById(R.id.txtCrearLatitudFinal);
        btnCrear = findViewById(R.id.btnCrear);

    }
    private boolean validacion(){
        String plongitudinicial = txtCrearLatitudInicial.getText().toString();
        String plongitudfinal = txtCrearLatitudFinal.getText().toString();
        String platitudinicial = txtCrearLatitudInicial.getText().toString();
        String platitudfinal = txtCrearLatitudFinal.getText().toString();
        if(plongitudinicial.isEmpty() ){
            txtCrearLongitudInicial.setError("Ingrese la longitud inicial");
            txtCrearLongitudInicial.requestFocus();
            return false;
        } else if (plongitudfinal.isEmpty()){
            txtCrearLongitudFinal.setError("Ingrese la longitud final");
            txtCrearLongitudFinal.requestFocus();
            return false;
        } else if (platitudinicial.isEmpty()){
            txtCrearLatitudInicial.setError("Ingrese la latitud inicial");
            txtCrearLatitudInicial.requestFocus();
            return false;
        } else if (platitudfinal.isEmpty()){
            txtCrearLatitudFinal.setError("Ingrese la latitud final");
            txtCrearLatitudFinal.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(CrearMapaActivity.this, menuActivity.class);
                startActivity(intent);
                break;
        }
        switch (menuItem.getItemId()) {
            case R.id.menu_nueva_rutas:
                Intent intent = new Intent(CrearMapaActivity.this, CrearMapaActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}