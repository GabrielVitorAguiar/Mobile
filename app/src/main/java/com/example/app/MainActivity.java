package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText edMin,edMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView =findViewById(R.id.textView);
        edMin=findViewById(R.id.edMin);
        edMax=findViewById(R.id.edMax);

        findViewById(R.id.button).setOnClickListener(v -> {
            String strMin = edMin.getText().toString();
            String strMax = edMax.getText().toString();

            if(strMin.isEmpty()){
                edMin.setError("Informe o valor minimo");
                edMin.requestFocus();
                return;
            }

            if(strMax.isEmpty()) {
                edMax.setError("Informe o valor máximo");
                edMax.requestFocus();
                return;
            }

            int min = Integer.parseInt(strMin);
            int max = Integer.parseInt(strMax);

            Random random = new Random();
            int r = random.nextInt(max - min)+min;
            textView.setText(Integer.toString(r));
        });

    }
}

