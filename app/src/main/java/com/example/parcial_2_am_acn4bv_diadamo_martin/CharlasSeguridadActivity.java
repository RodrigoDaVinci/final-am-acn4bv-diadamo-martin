package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CharlasSeguridadActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charlas_seguridad);

        mAuth = FirebaseAuth.getInstance();
    }

    public void openVideo1(View view) {
        openVideo("https://www.youtube.com/watch?v=EGIuCyrhXt4");
    }

    public void openVideo2(View view) {
        openVideo("https://www.youtube.com/watch?v=HQXfZJF0Vag");
    }

    public void openVideo3(View view) {
        openVideo("https://www.youtube.com/watch?v=zvF-v53Xr8E");
    }

    public void openVideo4(View view) {
        openVideo("https://www.youtube.com/watch?v=iTHaM4XTQkg");
    }

    public void openVideo5(View view) {
        openVideo("https://www.youtube.com/watch?v=lpj3A2Tj2ag");
    }

    public void openVideo6(View view) {
        openVideo("https://www.youtube.com/watch?v=_ewq-nceFX0");
    }

    private void openVideo(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
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
