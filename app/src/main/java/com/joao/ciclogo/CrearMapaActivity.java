package com.joao.ciclogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearMapaActivity extends AppCompatActivity {
    EditText txtCrearLongitudInicial, txtCrearLatitudInicial, txtCrearLongitudFinal, txtCrearLatitudFinal;
    Button btnCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mapa);
        asignarReferencias();
        btnCrear.setOnClickListener(view -> {
            if(validacion()){
                Intent intent = new Intent(CrearMapaActivity.this,SeguridadRutaActivity.class);
                intent.putExtra("plongitudinicial",txtCrearLongitudInicial.getText().toString()+"");
                intent.putExtra("plongitudfinal",txtCrearLongitudFinal.getText().toString()+"");
                intent.putExtra("platitudinicial",txtCrearLatitudInicial.getText().toString()+"");
                intent.putExtra("platitudfinal",txtCrearLatitudFinal.getText().toString()+"");
            }else{
                Toast.makeText(CrearMapaActivity.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            });
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
}