package com.example.newappbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import CountBank.ArticleAdapter;
import CountBank.Articulo;

public class MainActivity extends AppCompatActivity {
    List<Articulo>elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        elements=new ArrayList<>();
        elements.add(new Articulo("Case ","Asus ",230,R.drawable.case1));
        elements.add(new Articulo("Monitor ","Sansung ",235,R.drawable.monitor));
        elements.add(new Articulo("Teclado","Teraware",233,R.drawable.teclado));
        elements.add(new Articulo("Mouse","Teraware",231,R.drawable.mosue));
        elements.add(new Articulo("Procesador","Ryzen 7",231,R.drawable.procesador));
        elements.add(new Articulo("Memoria"," ram de 8GB",200,R.drawable.ram));
        elements.add(new Articulo("GPU","Nvidia",2401,R.drawable.gpu));
        ArticleAdapter articleAdapter=new ArticleAdapter(elements, com.example.newappbank.MainActivity.this, new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Articulo item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.List_Articulos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(articleAdapter);
    };
    public void moveToDescription(Articulo item){
        Intent intent=new Intent(com.example.newappbank.MainActivity.this,DescriptionActivity.class);
        intent.putExtra("Articulo",item);
        startActivity(intent);
    }
}