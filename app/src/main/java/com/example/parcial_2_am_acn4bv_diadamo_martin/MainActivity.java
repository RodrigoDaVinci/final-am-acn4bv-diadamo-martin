package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView dynamicImageView;
    private Button buttonContinuar;
    private String selectedOption;
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        // Referencias a los botones de las opciones
        Button buttonAgro = findViewById(R.id.buttonAgro);
        Button buttonPetrolera = findViewById(R.id.buttonPetrolera);
        Button buttonConstruccion = findViewById(R.id.buttonConstruccion);
        Button buttonIndustrial = findViewById(R.id.buttonIndustrial);
        Button buttonSalud = findViewById(R.id.buttonSalud);
        Button buttonEducacion = findViewById(R.id.buttonEducacion);
        Button buttonOficina = findViewById(R.id.buttonOficina);
        Button buttonMineria = findViewById(R.id.buttonMineria);
        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        //Continuar
        buttonContinuar = findViewById(R.id.buttonContinuar);

        buttonAgro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Agro"
                Toast.makeText(MainActivity.this, "Seleccionaste Agro", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.agro_image);
                selectedOption = "Agro";
            }
        });

        buttonPetrolera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Petrolera"
                Toast.makeText(MainActivity.this, "Seleccionaste Petrolera", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.petrolera_image);
                selectedOption = "Petrolera";
            }
        });

        buttonConstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Construccion"
                Toast.makeText(MainActivity.this, "Seleccionaste Construcción", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.construccion_image);
                selectedOption = "Construcción";
            }
        });

        buttonIndustrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Industrial"
                Toast.makeText(MainActivity.this, "Seleccionaste Industrial", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.industrial_image);
                selectedOption = "Industrial";
            }
        });

        buttonSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Salud"
                Toast.makeText(MainActivity.this, "Seleccionaste Salud", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.salud_image);
                selectedOption = "Salud";
            }
        });

        buttonEducacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Educacion"
                Toast.makeText(MainActivity.this, "Seleccionaste Educación", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.educacion_image);
                selectedOption = "Educación";
            }
        });

        buttonOficina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Oficina"
                Toast.makeText(MainActivity.this, "Seleccionaste Oficina", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.oficina_image);
                selectedOption = "Oficina";
            }
        });

        buttonMineria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en la opción "Mineria"
                Toast.makeText(MainActivity.this, "Seleccionaste Minería", Toast.LENGTH_SHORT).show();
                //Mostar imagen
                addDynamicImageView(R.drawable.mineria_image);
                selectedOption = "Minería";
            }
        });

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Continuar"
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("SELECTED_OPTION", selectedOption);
                startActivity(intent);
            }
        });

    }

    private void addDynamicImageView(int imageResource) {
        // Borrar la imagen
        if (dynamicImageView != null) {
            LinearLayout containerLayout = findViewById(R.id.mainContentLayout);
            containerLayout.removeView(dynamicImageView);
            dynamicImageView = null;
        }

        // Mostrar
        dynamicImageView = new ImageView(this);
        dynamicImageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        dynamicImageView.setImageResource(imageResource);

        // Obtener el contenedor LinearLayout desde XML
        LinearLayout containerLayout = findViewById(R.id.mainContentLayout);

        // Agregar el ImageView al contenedor LinearLayout
        containerLayout.addView(dynamicImageView);
        buttonContinuar.setVisibility(View.VISIBLE);
    }

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}