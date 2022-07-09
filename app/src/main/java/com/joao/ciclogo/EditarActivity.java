package com.joao.ciclogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joao.ciclogo.entidad.Rutas;

import java.util.HashMap;

public class EditarActivity extends AppCompatActivity {
    Button btn_return_mod,btnModificar;
    EditText txtEditarNombre, txtEditarLongitudInicial, txtEditarLatitudInicial, txtEditarLongitudFinal, txtEditarLatitudFinal;
    RadioGroup rd_editar_segruta;
    RadioButton rb_editar_segura, rb_editar_regular, rb_editar_peligrosa, rb_seleccionado;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int seleccionado = 0;
    String id;
    HashMap map = new HashMap();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        asignarReferencias();
        inicializarFirebase();
        obtenerValores();
    }

    private void obtenerValores() {
        id = getIntent().getStringExtra("pid");
        txtEditarNombre.setText(getIntent().getStringExtra("pnombre"));
        txtEditarLongitudInicial.setText(getIntent().getStringExtra("plongitudinicio"));
        txtEditarLatitudInicial.setText(getIntent().getStringExtra("platitudinicio"));
        txtEditarLongitudFinal.setText(getIntent().getStringExtra("plongitudfinal"));
        txtEditarLatitudFinal.setText(getIntent().getStringExtra("platitudfinal"));
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void asignarReferencias(){
        btn_return_mod = findViewById(R.id.btn_return_mod);
        btnModificar = findViewById(R.id.btnModificar);
        txtEditarNombre = findViewById(R.id.txtEditarNombre);
        txtEditarLongitudInicial = findViewById(R.id.txtEditarLongitudInicial);
        txtEditarLatitudInicial = findViewById(R.id.txtEditarLatitudInicial);
        txtEditarLongitudFinal = findViewById(R.id.txtEditarLongitudFinal);
        txtEditarLatitudFinal = findViewById(R.id.txtEditarLatitudFinal);
        rd_editar_segruta = findViewById(R.id.rd_editar_segruta);
        rb_editar_segura = findViewById(R.id.rb_editar_segura);
        rb_editar_regular = findViewById(R.id.rb_editar_regular);
        rb_editar_peligrosa = findViewById(R.id.rb_editar_peligrosa);
        btn_return_mod.setOnClickListener(v -> {
            Intent intent = new Intent(EditarActivity.this, menuActivity.class);
            startActivity(intent);
        });
        btnModificar.setOnClickListener(view -> {
            String mensaje = "¿Estas seguro de modificar la ruta?";
            seleccionado = rd_editar_segruta.getCheckedRadioButtonId();
            rb_seleccionado = findViewById(seleccionado);
            capturarDatos();
            databaseReference.child("Rutas").child(id).updateChildren(map);
            AlertDialog.Builder builder = new AlertDialog.Builder(EditarActivity.this);
            builder.setTitle("Confirmación");
            builder.setMessage(mensaje);
            builder.setPositiveButton("Aceptar",((dialogInterface, i) -> {
                finish();
            }));
            builder.create().show();
        });
    }

    private void capturarDatos() {
        String nombre = txtEditarNombre.getText().toString();
        String longitudInicial = txtEditarLongitudInicial.getText().toString();
        String latitudInicial = txtEditarLatitudInicial.getText().toString();
        String longitudFinal = txtEditarLongitudFinal.getText().toString();
        String latitudFinal = txtEditarLatitudFinal.getText().toString();
        String tipo = rb_seleccionado.getText().toString();
        map.put("latitud_final", latitudFinal);
        map.put("longitud_final", longitudFinal);
        map.put("latitud_inicio", latitudInicial);
        map.put("longitud_inicio", longitudInicial);
        map.put("nombre", nombre);
        map.put("peligrosidad", tipo);
    }
}