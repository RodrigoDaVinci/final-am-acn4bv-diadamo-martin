package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Obtiene los datos pasados desde MainActivity
        selectedOption  = getIntent().getStringExtra("SELECTED_OPTION");

        // Muestra la opción seleccionada en el TextView
        TextView textViewSelectedOption = findViewById(R.id.textViewSelectedOption);
        textViewSelectedOption.setText("Opción seleccionada: " + selectedOption);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        configureButtonClick(R.id.buttonCheckListSeguridad, CheckListSeguridadActivity.class, selectedOption);
        configureButtonClick(R.id.buttonCharlasSeguridad, CharlasSeguridadActivity.class, selectedOption);
        configureButtonClick(R.id.buttonNormativa, NormativaActivity.class, selectedOption);
        configureButtonClick(R.id.buttonDenunciaAccidente, DenunciaAccidenteActivity.class, selectedOption);
        configureButtonClick(R.id.buttonCapacitaciones, CapacitacionesActivity.class, selectedOption);
    }

    private void configureButtonClick(int buttonId, final Class<?> targetActivity, final String selectedOption) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, targetActivity);
                intent.putExtra("SELECTED_OPTION", selectedOption);
                startActivity(intent);
            }
        });
    }
}