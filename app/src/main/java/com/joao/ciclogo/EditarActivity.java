package com.joao.ciclogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditarActivity extends AppCompatActivity {
    EditText txtEditarCreador, txtEditarNombre,
            txtEditarLongitudInicial, txtEditarLatitudInicial,
            txtEditarLongitudFinal, txtEditarLatitudFinal;
    RadioGroup rd_editar_segruta;
    RadioButton rb_editar_segura, rb_editar_regular, rb_editar_peligrosa, rb_editar_seleccionado;
    Button btnModificar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;
    int seleccionado = 0;
    HashMap map = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        asignarReferencias();
    }
    private void obtenerValores(){
        id = getIntent().getStringExtra("pid");
        txtEditarCreador.setText(getIntent().getStringExtra("pcreador"));
        txtEditarNombre.setText(getIntent().getStringExtra("pnombre"));
        txtEditarLatitudInicial.setText(getIntent().getStringExtra("platitudinicio"));
        txtEditarLongitudInicial.setText(getIntent().getStringExtra("plogintudinicio"));
        txtEditarLatitudFinal.setText(getIntent().getStringExtra("platitudfinal"));
        txtEditarLongitudFinal.setText(getIntent().getStringExtra("plongitudfinal"));

        if(getIntent().getStringExtra("ppeligrosidad").equals("Peligroso")){
            rb_editar_peligrosa.setChecked(true);
        }else if(getIntent().getStringExtra("ppeligrosidad").equals("Regular")){
            rb_editar_regular.setChecked(true);
        }else{
            rb_editar_segura.setChecked(true);
        }

    }
    private void asignarReferencias(){
        txtEditarCreador = findViewById(R.id.txtEditarCreador);
        txtEditarNombre = findViewById(R.id.txtEditarNombre);
        txtEditarLongitudInicial = findViewById(R.id.txtEditarLongitudInicial);
        txtEditarLatitudInicial = findViewById(R.id.txtEditarLatitudInicial);
        txtEditarLongitudFinal = findViewById(R.id.txtEditarLongitudFinal);
        txtEditarLatitudFinal = findViewById(R.id.txtEditarLatitudFinal);
        rd_editar_segruta = findViewById(R.id.rd_editar_segruta);
        rb_editar_segura = findViewById(R.id.rb_editar_segura);
        rb_editar_regular = findViewById(R.id.rb_editar_regular);
        rb_editar_peligrosa = findViewById(R.id.rb_editar_peligrosa);
        btnModificar = findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(view -> {
            if(seleccionado!=1){
                rb_editar_seleccionado = (RadioButton) findViewById(seleccionado);
            }
            capturarDatos();
            String mensaje = "";
            databaseReference.child("Ruta").child(id).updateChildren(map);
        });
    }
    private void capturarDatos(){
        String creador = txtEditarCreador.getText().toString();
        String nombre = txtEditarNombre.getText().toString();
        String longitudInicial = txtEditarLongitudInicial.getText().toString();
        String longitudFinal = txtEditarLongitudFinal.getText().toString();
        String latitudInicial = txtEditarLatitudInicial.getText().toString();
        String latitudFinal = txtEditarLatitudFinal.getText().toString();
        map.put("creador",creador);
        map.put("nombre",nombre);
        map.put("longitudInicial",longitudInicial);
        map.put("longitudFinal",longitudFinal);
        map.put("latitudInicial",latitudInicial);
        map.put("latitudFinal",latitudFinal);
    }
}