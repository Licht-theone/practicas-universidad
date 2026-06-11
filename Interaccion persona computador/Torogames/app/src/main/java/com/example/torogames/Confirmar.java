package com.example.torogames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Confirmar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Recuperar datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String apellidos = intent.getStringExtra("apellidos");
        String domicilio = intent.getStringExtra("domicilio");
        String dni = intent.getStringExtra("dni");
        String email = intent.getStringExtra("email");
        String cuentaBancaria = intent.getStringExtra("cuentaBancaria");
        // Asignar a cada TextView
        ((TextView) findViewById(R.id.tvNombre)).setText("Nombre: " + nombre);
        ((TextView) findViewById(R.id.tvApellidos)).setText("Apellidos: " + apellidos);
        ((TextView) findViewById(R.id.tvDomicilio)).setText("Domicilio: " + domicilio);
        ((TextView) findViewById(R.id.tvDNI)).setText("DNI: " + dni);
        ((TextView) findViewById(R.id.tvEMAIL)).setText("Email: " + email);
        ((TextView) findViewById(R.id.tvBanco)).setText("Cuenta Bancaria: " + cuentaBancaria);
    }

    public void lanzarMenu(View view) {
        Intent lanzadorMenu = new Intent(this, Menu.class);
        startActivity(lanzadorMenu);
    }
}