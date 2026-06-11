package com.example.torogames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Vender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vender);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void enviarDato(View view) {
        EditText etTitulojuego = findViewById(R.id.etTitulojuego);
        String tituloJuego = etTitulojuego.getText().toString();
        Intent intent = new Intent(this, ConfirmarVenta.class);
        intent.putExtra("tituloJuego", tituloJuego);
        startActivity(intent);
    }

    public void lanzarConfirmarVenta(View view) {
        Intent lanzadorConfirmarVenta = new Intent(this, ConfirmarVenta.class);
        enviarDato(view);
    }


}