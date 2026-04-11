package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnAnterior, btnProximo;
    ImageView imageView;
    int fotos[] = new int[]{R.drawable.cachorro,R.drawable.gardem,R.drawable.happy,R.drawable.porquinho,R.drawable.patinho};
    int posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnAnterior=findViewById(R.id.anterior);
        btnProximo=findViewById(R.id.proximo);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(fotos[posicao]);

        btnProximo.setOnClickListener(v -> {
            posicao++;
            if(posicao > fotos.length-1){
                posicao=0;
            }
            imageView.setImageResource(fotos[posicao]);
        });

        btnAnterior.setOnClickListener(v -> {
            posicao --;
            if(posicao < 0 ){
                posicao=fotos.length-1;
            }
            imageView.setImageResource(fotos[posicao]);
        });


    }
}

