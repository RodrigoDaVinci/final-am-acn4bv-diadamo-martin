package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private String selectedOption;
    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        configureButtonClick(R.id.buttonCheckListSeguridad, CheckListSeguridadActivity.class, selectedOption);
        configureButtonClick(R.id.buttonCharlasSeguridad, CharlasSeguridadActivity.class, selectedOption);
        configureButtonClick(R.id.buttonNormativa, NormativaActivity.class, selectedOption);
        configureButtonClick(R.id.buttonDenunciaAccidente, DenunciaAccidenteActivity.class, selectedOption);
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

    public void showDropdownMenu(View view) {
        // Crear el PopupMenu
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_dropdown, popupMenu.getMenu());

        // Configurar el listener para el botón de cerrar sesión
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_logout) {
                // Manejar el clic en el botón de cerrar sesión
                logout();
                return true;
            }
            return false;
        });

        // Mostrar el menú
        popupMenu.show();
    }

    private void logout() {
        mAuth.signOut();
        Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}