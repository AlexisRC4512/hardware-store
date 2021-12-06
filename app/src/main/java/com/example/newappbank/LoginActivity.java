package com.example.newappbank;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import CountBank.Count;

public class LoginActivity extends AppCompatActivity {
    TextView bienvenido,continuarLabel,nuevoUsuario;
    ImageView Loginimg;
    TextInputLayout usuarioT,contraseña;
    MaterialButton inicioSesion;
    TextInputEditText email1,pass2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Loginimg=findViewById(R.id.logoImg);
        bienvenido=findViewById(R.id.txvGreenB);
        continuarLabel=findViewById(R.id.ContinuarLabel);
        usuarioT=findViewById(R.id.UsuarioTextField);
        contraseña=findViewById(R.id.ContraseñaTextField);
        inicioSesion=findViewById(R.id.inicioSesion);
        nuevoUsuario=findViewById(R.id.nuevoUsuario);
        email1=findViewById(R.id.emailText1);
        pass2=findViewById(R.id.passwText1);
        mAuth=FirebaseAuth.getInstance();
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               transition1();
            }
        });
    }
    public void transition1(){
        Intent intent=new Intent(com.example.newappbank.LoginActivity.this,ActivityCre.class);

            startActivity(intent);

    }
    public void validate(){
       String email=email1.getText().toString().trim();
       String pass=pass2.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email1.setError("Correo invalido");
            return;
        }else {
            email1.setError(null);
        }

        if (pass.isEmpty() || pass.length()<8){
            pass2.setError("Se necesitan más de 8 caracteres");
            return;
        }else if (!Pattern.compile("[0-9]").matcher(pass).find()){
            pass2.setError("Al menos un número");
            return;
        }else {
            pass2.setError(null);
        }
        iniciarSecion(email,pass);
    }

    public void iniciarSecion(String email,String pass){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(com.example.newappbank.LoginActivity.this,com.example.newappbank.MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Error de contraseña o correo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    };
}