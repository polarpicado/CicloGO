package com.joao.ciclogo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText txt_user, txt_password;
    Button btn_iniciar,btn_return,btn_iniciar_facebook,btn_iniciar_twitter,btn_iniciar_google;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        asignarReferencias();
        btn_iniciar.setOnClickListener(view -> {
            logearUsuario();
        });
        btn_return.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        });
        btn_iniciar_facebook.setOnClickListener(view -> {
            logearUsuarioFacebook();
        });
        btn_iniciar_twitter.setOnClickListener(view -> {
            logearUsuarioTwitter();
        });
        btn_iniciar_google.setOnClickListener(view -> {
            logearUsuarioGoogle();
        });
    }
    private void asignarReferencias(){
        txt_user = findViewById(R.id.txt_user);
        txt_password = findViewById(R.id.txt_password);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        btn_return = findViewById(R.id.btn_return);
        btn_iniciar_facebook = findViewById(R.id.btn_iniciar_facebook);
        btn_iniciar_twitter = findViewById(R.id.btn_iniciar_twitter);
        btn_iniciar_google = findViewById(R.id.btn_iniciar_google);
    }
    private void logearUsuario(){
        String email = txt_user.getText().toString().trim();
        String contra = txt_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingresa un correo electrónico.",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(contra)) {
            Toast.makeText(this, "Ingresa una contraseña.", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Procesando solicitud...", Toast.LENGTH_LONG).show();
        firebaseAuth.signInWithEmailAndPassword(email, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"Sesión iniciada correctamente.",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, " Fallo al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void logearUsuarioFacebook(){

    }
    private void logearUsuarioTwitter(){

    }
    private void logearUsuarioGoogle() {

    }
}