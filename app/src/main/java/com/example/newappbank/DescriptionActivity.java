package com.example.newappbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import CountBank.Articulo;

public class DescriptionActivity extends AppCompatActivity {
    TextView name,descrip,precio;
    ImageView imgArt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Articulo element=(Articulo) getIntent().getSerializableExtra("Articulo");
        name=findViewById(R.id.Nametxv);
        imgArt=findViewById(R.id.imageArt);
        precio=findViewById(R.id.preciotxv);
        descrip=findViewById(R.id.DecriptiontArtxv);
        name.setText(element.getNombre());
        imgArt.setImageResource(element.getImg());
        precio.setText(String.valueOf(element.getPrecio()));
        descrip.setText(element.getDescripcion());
    }
}