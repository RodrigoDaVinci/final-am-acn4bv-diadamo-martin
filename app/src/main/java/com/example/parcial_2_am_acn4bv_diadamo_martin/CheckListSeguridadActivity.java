package com.example.parcial_2_am_acn4bv_diadamo_martin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckListSeguridadActivity extends AppCompatActivity {

    private Spinner spinnerTareas;
    private LinearLayout linearLayoutTareas;
    private Button buttonEnviar;
    private String selectedOption;
    private Map<String, List<String>> tasksMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list_seguridad);

        // Obtiene la opción seleccionada de la actividad anterior
        selectedOption = getIntent().getStringExtra("SELECTED_OPTION");

        // Configura el mapa de tareas
        tasksMap = new HashMap<>();
        tasksMap.put("Agro", getTasksForAgro());
        tasksMap.put("Petrolera", getTasksForPetrolera());
        // Agrega más opciones y tareas según sea necesario

        // Inicializa vistas
        spinnerTareas = findViewById(R.id.spinnerTareas);
        linearLayoutTareas = findViewById(R.id.linearLayoutTareas);
        buttonEnviar = findViewById(R.id.buttonEnviar);

        // Configura el Spinner con las tareas disponibles
        List<String> tasks = tasksMap.get(selectedOption);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tasks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTareas.setAdapter(adapter);

        // Configura el botón de enviar
        buttonEnviar.setOnClickListener(v -> sendEmail());

        // Configura la selección del Spinner
        spinnerTareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTask = tasks.get(position);
                showSubTasks(selectedTask);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private List<String> getTasksForAgro() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Revisar maquinaria");
        tasks.add("Controlar fertilizantes");
        tasks.add("Verificar riego");
        return tasks;
    }

    private List<String> getTasksForPetrolera() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Inspeccionar plataformas");
        tasks.add("Control de válvulas");
        tasks.add("Monitorear presión");
        return tasks;
    }

    // Agrega más métodos getTasksFor... para otras opciones

    private void showSubTasks(String task) {
        // Limpia las tareas anteriores
        linearLayoutTareas.removeAllViews();

        // Obtener las tareas específicas para la categoría seleccionada
        List<String> subTasks = getSubTasks(task);

        // Agrega las nuevas tareas
        for (String subTask : subTasks) {
            Switch switchTask = new Switch(this);
            switchTask.setText(subTask);
            linearLayoutTareas.addView(switchTask);
        }
    }

    private List<String> getSubTasks(String task) {
        List<String> subTasks = new ArrayList<>();
        switch (task) {
            case "Revisar maquinaria":
                subTasks.add("Revisar maquinaria - Revisar manguera");
                subTasks.add("Revisar maquinaria - Verificar lluvia");
                // Agrega más subtareas específicas para Agro según sea necesario
                break;
            case "Inspeccionar plataformas":
                subTasks.add("Inspeccionar plataformas - Inspección visual");
                subTasks.add("Inspeccionar plataformas - Revisar válvulas");
                // Agrega más subtareas específicas para Petrolera según sea necesario
                break;
            // Agrega más casos para otras categorías si es necesario
            default:
                // En caso de no coincidir con ninguna categoría específica, retorna una lista vacía o genérica
                subTasks.add("Realizar tarea específica");
                break;
        }
        return subTasks;
    }

    private void sendEmail() {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Tareas de ").append(selectedOption).append(":\n\n");

        // Recorre las tareas y verifica si fueron realizadas o no
        for (int i = 0; i < linearLayoutTareas.getChildCount(); i++) {
            Switch switchTask = (Switch) linearLayoutTareas.getChildAt(i);
            emailContent.append(switchTask.getText().toString())
                    .append(": ")
                    .append(switchTask.isChecked() ? "Realizado" : "No realizado")
                    .append("\n");
        }

        // Crea un intent para enviar el email
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Lista de tareas de seguridad");
        intent.putExtra(Intent.EXTRA_TEXT, emailContent.toString());

        try {
            startActivity(Intent.createChooser(intent, "Enviar email usando..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(CheckListSeguridadActivity.this, "No hay clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}