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

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    Button button;
    EditText edPeso,edAltura;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edAltura = findViewById(R.id.edAltura);
        edPeso = findViewById(R.id.edPeso);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String strAltura = edAltura.getText().toString();
            String strPeso = edPeso.getText().toString();

            if(strAltura.isEmpty()){
                edAltura.setError("Informe a altura!");
                edAltura.requestFocus();
                return;
            }

            if(strPeso.isEmpty()){
                edPeso.setError("Informe a altura!");
                edPeso.requestFocus();
                return;
            }

            double peso = Double.parseDouble(strPeso);
            double altura = Double.parseDouble(strAltura);

            double imc = peso / (altura*altura);
            DecimalFormat dc = new DecimalFormat("##.##");
            textView.setText(dc.format(imc));

            /*IMC abaixo de 18,5: Abaixo do peso
IMC entre 18,5 e 24,9: Peso normal
IMC entre 25 e 29,9: Sobrepeso
IMC entre 30 e 34,9: Obesidade grau 1
IMC entre 35 e 39,9: Obesidade grau 2
IMC acima de 40: Obesidade grau 3*/

            if(imc < 18.5){
              imageView.setImageResource(R.drawable.abaixopeso);
              return;
            };
            if(imc < 25){
                imageView.setImageResource(R.drawable.normal);
                return;
            }
            if(imc < 30){
                imageView.setImageResource(R.drawable.sobrepeso);
                return;
            }
            if(imc < 35 ){
                imageView.setImageResource(R.drawable.obesidade1);
                return;
            }
            if(imc < 40){
                imageView.setImageResource(R.drawable.obesidade2);
                return;
            }

            imageView.setImageResource(R.drawable.obesidade3);

        });




    }
}

