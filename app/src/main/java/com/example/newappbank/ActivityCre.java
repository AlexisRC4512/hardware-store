package com.example.newappbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import CountBank.Count;

public class ActivityCre extends AppCompatActivity {
    String user,email,password,conPass;
    TextView nuevoUsuario,bienvenido,continuarLabel;
    ImageView singUpimg;
    TextInputLayout usuario,contraseña;
    MaterialButton inicioSesion;
    private FirebaseAuth mAuth;
    Count objC1;

    TextInputEditText emailText,passwordText,confirmPassText,userText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cre);
        singUpimg=findViewById(R.id.SingUpimg);
        bienvenido=findViewById(R.id.BienvenidoLabel);
        continuarLabel=findViewById(R.id.ContinuarLabel);
        usuario=findViewById(R.id.UsuarioSignTextField);
        contraseña=findViewById(R.id.ContraseñaTextField);
        inicioSesion=findViewById(R.id.inicioSesion);
        nuevoUsuario=findViewById(R.id.nuevoUsuario);
        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passText);
        confirmPassText=findViewById(R.id.conPassText);
        userText=findViewById(R.id.nameText);


        nuevoUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }

        });
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void onBackPressed(){
        transitionBack();
    }
    public void transitionBack(){
        Intent intent=new Intent(com.example.newappbank.ActivityCre.this,LoginActivity.class);
        Pair[]pairs=new Pair[7];
        pairs[0]=new Pair<View,String>(singUpimg,"logoImagenTra");
        pairs[1]=new Pair<View,String>(bienvenido,"textTrans");
        pairs[2]=new Pair<View,String>(continuarLabel,"inisiarSesionTra");
        pairs[3]=new Pair<View,String>(usuario,"emailTra");
        pairs[4]=new Pair<View,String>(contraseña,"passTra");
        pairs[5]=new Pair<View,String>(inicioSesion,"inicioSignTra");
        pairs[6]=new Pair<View,String>(nuevoUsuario,"newUserTra");
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(com.example.newappbank.ActivityCre.this,pairs);
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
            finish();
        }
    }
    public void validate(){
        user=userText.getText().toString().trim();
        email=emailText.getText().toString().trim();
         password=passwordText.getText().toString().trim();
        conPass=confirmPassText.getText().toString().trim();
         objC1=new Count(email,conPass,user);
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailText.setError("Correo invalido");
                return;
            }else {
                emailText.setError(null);
            }

            if (password.isEmpty() || password.length()<8){
                passwordText.setError("Se necesitan más de 8 caracteres");
                return;
            }else if (!Pattern.compile("[0-9]").matcher(password).find()){
                passwordText.setError("Al menos un número");
                return;
            }else {
                passwordText.setError(null);
            }
            if (!conPass.equals(password)){
                confirmPassText.setError("Deben ser iguales");
                return;
            }else {
                registrar(objC1.getUser(),objC1.getPass());
            }
    }
    public void registrar(String email,String password){
        mAuth.createUserWithEmailAndPassword(objC1.getUser(),objC1.getPass()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                        Intent intent=new Intent(com.example.newappbank.ActivityCre.this,com.example.newappbank.MainActivity.class);
                    startActivity(intent);
                    finish();


                }else {
                    Toast.makeText(getApplicationContext(),"No se pudo registrar el usuario"+objC1.getUser(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}