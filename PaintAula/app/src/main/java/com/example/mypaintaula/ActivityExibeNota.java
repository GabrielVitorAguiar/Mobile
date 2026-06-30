package com.example.mypaintaula;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityExibeNota extends AppCompatActivity {

    private EditText etTitulo;
    private EditText etTexto;

    private Button btnSalvar;
    private Button btnExcluir;

    private NotaController controller;

    private int idNota = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_nota);

        etTitulo = findViewById(R.id.etTitulo);
        etTexto = findViewById(R.id.etTexto);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        controller = new NotaController(this);

        if(getIntent().hasExtra("id")){

            idNota = getIntent().getIntExtra("id",-1);

            Nota nota = controller.buscarPorId(idNota);

            if(nota != null){

                etTitulo.setText(nota.getTitulo());
                etTexto.setText(nota.getTexto());

            }

        }else{

            btnExcluir.setEnabled(false);

        }

        btnSalvar.setOnClickListener(v -> salvar());

        btnExcluir.setOnClickListener(v -> excluir());

    }

    private void salvar(){

        String titulo = etTitulo.getText().toString().trim();
        String texto = etTexto.getText().toString().trim();

        if(titulo.isEmpty()){

            Toast.makeText(
                    this,
                    "Informe o título.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Nota nota = new Nota();

        nota.setTitulo(titulo);
        nota.setTexto(texto);

        if(idNota == -1){

            controller.salvar(nota);

            Toast.makeText(
                    this,
                    "Nota cadastrada.",
                    Toast.LENGTH_SHORT
            ).show();

        }else{

            nota.setId(idNota);

            controller.atualizar(nota);

            Toast.makeText(
                    this,
                    "Nota atualizada.",
                    Toast.LENGTH_SHORT
            ).show();

        }

        finish();

    }

    private void excluir(){

        controller.excluir(idNota);

        Toast.makeText(
                this,
                "Nota excluída.",
                Toast.LENGTH_SHORT
        ).show();

        finish();

    }

}