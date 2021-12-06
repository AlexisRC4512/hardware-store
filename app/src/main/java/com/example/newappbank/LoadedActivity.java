package com.example.newappbank;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoadedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loaded);
        //AGREGAR ANIMACIONES
        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2=AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
        ImageView imgmov=(ImageView) findViewById(R.id.logoImg);
        TextView title=(TextView)findViewById(R.id.txvGreenB);
        imgmov.setAnimation(animacion1);
        title.setAnimation(animacion2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(com.example.newappbank.LoadedActivity.this,LoginActivity.class);
                Pair[]pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(imgmov,"logoImagenTra");
                pairs[1]=new Pair<View,String>(title,"textTrans");
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(com.example.newappbank.LoadedActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }else{
                    startActivity(intent);
                    finish();
                }
            }
        },4000);
    }
}