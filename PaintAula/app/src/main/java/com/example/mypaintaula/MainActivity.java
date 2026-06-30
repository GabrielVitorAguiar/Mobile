package com.example.mypaintaula;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnNovaNota;
    private ListView listNotas;

    private NotaController controller;
    private ArrayList<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovaNota = findViewById(R.id.btnNovaNota);
        listNotas = findViewById(R.id.listNotas);

        controller = new NotaController(this);

        btnNovaNota.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ActivityExibeNota.class
            );

            startActivity(intent);

        });

        listNotas.setOnItemClickListener((parent, view, position, id) -> {

            Nota nota = listaNotas.get(position);

            Intent intent = new Intent(
                    MainActivity.this,
                    ActivityExibeNota.class
            );

            intent.putExtra("id", nota.getId());

            startActivity(intent);

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {

        listaNotas = controller.listar();

        ArrayAdapter<Nota> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaNotas
        );

        listNotas.setAdapter(adapter);

    }

}