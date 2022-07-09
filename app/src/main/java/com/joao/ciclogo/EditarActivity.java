package com.joao.ciclogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joao.ciclogo.entidad.Rutas;

import java.util.HashMap;

public class EditarActivity extends AppCompatActivity {
    private EditText txtEditarCreador, txtEditarNombre,
            txtEditarLongitudInicial, txtEditarLatitudInicial,
            txtEditarLongitudFinal, txtEditarLatitudFinal;
    private RadioGroup rd_editar_segruta;
    private RadioButton rb_editar_segura, rb_editar_regular, rb_editar_peligrosa, rb_editar_seleccionado;
    private Button btnModificar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String id;
    private HashMap map = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        asignarReferencias(); //Para que se asigne las referencias a los elementos de la vista
        inicializarFirebase(); //Para que se pueda conectar con la base de datos
        obtenerValores(); //Para que se llene con los valores de la ruta que se va a editar
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
        }else if(getIntent().getStringExtra("ppeligrosidad").equals("Regular")) {
            rb_editar_regular.setChecked(true);
        }else if(getIntent().getStringExtra("ppeligrosidad").equals("Seguro")) {
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
            capturarDatos();
            String mensaje = "";
            databaseReference.child("Rutas").child(id).updateChildren(map);
            mensaje = "Ruta modificada correctamente";
            AlertDialog.Builder builder = new AlertDialog.Builder(EditarActivity.this);
            builder.setTitle("Mensaje");
            builder.setMessage(mensaje);
            builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {
                finish();
            });
            builder.create().show();
        });
    }
    private void capturarDatos(){
        String creador = txtEditarCreador.getText().toString();
        String nombre = txtEditarNombre.getText().toString();
        String longitudInicial = txtEditarLongitudInicial.getText().toString();
        String longitudFinal = txtEditarLongitudFinal.getText().toString();
        String latitudInicial = txtEditarLatitudInicial.getText().toString();
        String latitudFinal = txtEditarLatitudFinal.getText().toString();
        String peligrosidad = rd_editar_segruta.getCheckedRadioButtonId() == R.id.rb_editar_segura ? "Segura" :
                rd_editar_segruta.getCheckedRadioButtonId() == R.id.rb_editar_regular ? "Regular" : "Peligroso";
        map.put("creador",creador);
        map.put("nombre",nombre);
        map.put("longitudInicial",longitudInicial);
        map.put("longitudFinal",longitudFinal);
        map.put("latitudInicial",latitudInicial);
        map.put("latitudFinal",latitudFinal);
        map.put("peligrosidad",peligrosidad);
    }

    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}