package com.example.mypaintaula;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    private SimplePaint simplePaint;
    private ImageView ivColorPicker;
    private Button btForma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);
        ivColorPicker = findViewById(R.id.ivColorPicker);
        btForma = findViewById(R.id.btForma);

        ivColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickerSelectColor();
            }
        });

        btForma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarForma();
            }
        });
    }

    private void selecionarForma() {

        String[] opcoes = {
                "Linha Livre",
                "Retângulo",
                "Círculo"
        };

        new AlertDialog.Builder(this)
                .setTitle("Escolha a forma")
                .setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:
                                simplePaint.setTipoDesenho(SimplePaint.LINHA);
                                break;

                            case 1:
                                simplePaint.setTipoDesenho(SimplePaint.RETANGULO);
                                break;

                            case 2:
                                simplePaint.setTipoDesenho(SimplePaint.CIRCULO);
                                break;
                        }
                    }
                })
                .show();
    }

    public void colorPickerSelectColor() {

        new ColorPickerDialog.Builder(this)
                .setTitle("Escolha uma cor")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(
                        getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(
                                    ColorEnvelope envelope,
                                    boolean fromUser
                            ) {
                                setColor(envelope);
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }
                )
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show();
    }

    private void setColor(ColorEnvelope envelope) {

        simplePaint.setColor(
                Color.valueOf(envelope.getColor())
        );

        ivColorPicker.setColorFilter(
                Color.valueOf(envelope.getColor()).toArgb()
        );
    }
}