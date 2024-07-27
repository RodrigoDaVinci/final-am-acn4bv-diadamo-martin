package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CharlasSeguridadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charlas_seguridad);

        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
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

    private void logout() {
        Intent intent = new Intent(CharlasSeguridadActivity.this, Login.class);
        startActivity(intent);
        finish();
    }
}
