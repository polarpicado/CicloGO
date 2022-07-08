package com.joao.ciclogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegistrarActivity extends AppCompatActivity {
    TextView txt_correo, txt_contra_registro;
    Button btn_registro,btn_regresar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        firebaseAuth = FirebaseAuth.getInstance();
        asignarReferencias();
        btn_registro.setOnClickListener(view -> {
            registrarUsuario();
        });
        btn_regresar.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrarActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }
    private void asignarReferencias(){
        txt_correo = findViewById(R.id.txt_correo);
        txt_contra_registro = findViewById(R.id.txt_contra_registro);
        btn_registro = findViewById(R.id.btn_registro);
        btn_regresar = findViewById(R.id.btn_regresar);
    }
    private void registrarUsuario(){
        String email = txt_correo.getText().toString().trim();
        String contra = txt_contra_registro.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingresa un correo electrónico.",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contra)) {
            Toast.makeText(this, "Ingresa una contraseña.", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Procesando solicitud...", Toast.LENGTH_LONG).show();
        firebaseAuth.createUserWithEmailAndPassword(email,contra).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Toast.makeText(this,"Registro correcto.",Toast.LENGTH_LONG).show();
            }else{
                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(this,"Usuario ya existe.",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Registro erróneo.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}