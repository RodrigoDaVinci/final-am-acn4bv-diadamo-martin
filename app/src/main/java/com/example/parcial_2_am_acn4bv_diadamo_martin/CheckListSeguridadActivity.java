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
        // Agrega más opciones
        tasksMap.put("Industrial", getTasksForIndustrial());
        tasksMap.put("Construcción", getTasksForConstruccion());
        tasksMap.put("Salud", getTasksForSalud());
        tasksMap.put("Educación", getTasksForEducacion());
        tasksMap.put("Oficina", getTasksForOficina());
        tasksMap.put("Minería", getTasksForMineria());

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
        tasks.add("Protección tractor");
        tasks.add("Depósitos fitosanitarios");
        tasks.add("Elementos de protección personal");
        return tasks;
    }

    private List<String> getTasksForPetrolera() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Inspeccionar plataformas");
        tasks.add("Control de válvulas");
        tasks.add("Elementos de protección Personal");
        return tasks;
    }

    private List<String> getTasksForIndustrial() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Control de máquinas");
        tasks.add("Control de válvulas");
        tasks.add("Elementos de protección personal");
        return tasks;
    }

    private List<String> getTasksForConstruccion() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Trabajo en altura");
        tasks.add("Demolición y excavación");
        tasks.add("Elementos de protección personal");
        return tasks;
    }

    private List<String> getTasksForSalud() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Residuos especiales");
        tasks.add("Movimientos de pacientes");
        tasks.add("Elementos de protección personal");
        return tasks;
    }

    private List<String> getTasksForEducacion() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Uso de la voz");
        tasks.add("Puesto de trabajo");
        tasks.add("Controles de salud");
        return tasks;
    }

    private List<String> getTasksForOficina() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Puesto de trabajo");
        tasks.add("Pausas activas");
        return tasks;
    }

    private List<String> getTasksForMineria() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Inspeccionar vehículo");
        tasks.add("Control de válvulas");
        tasks.add("Elementos de protección personal");
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
            case "Protección tractor":
                subTasks.add("Tiene barra antivuelco");
                subTasks.add("Tiene cinturón");
                subTasks.add("Tiene alarma sonora lumínica");
                subTasks.add("Luces reglamentarias");
                subTasks.add("Espejo retrovisor");
                // Agrega más subtareas específicas para Agro según sea necesario
                break;
            case "Depósitos fitosanitarios":
                subTasks.add("Estantería anticombustible");
                subTasks.add("Separación sólido-líquido");
                subTasks.add("Señalética no ingresar personal autorizado");
                subTasks.add("Cumple con las fichas de datos de seguridad SGA");
                subTasks.add("Tiene lava ojos");
                // Agrega más subtareas específicas para Petrolera según sea necesario
                break;
            case "Elementos de protección personal":
                subTasks.add("Botas de trabajo impermeables");
                subTasks.add("Calzado con puntera de protección");
                subTasks.add("Delantal impermeable");
                subTasks.add("Delantal de cuero");
                subTasks.add("Guantes para productos químicos puño largo");
                subTasks.add("Guantes de descarne");
                subTasks.add("Guantes malla anticorte");
                subTasks.add("Traje descartable");
                subTasks.add("Ropa de trabajo altavisibilidad");
                subTasks.add("Antiparras");
                subTasks.add("Protección ocular con protección lateral");
                subTasks.add("Protección facial y ocular para fundidor");
                subTasks.add("Protección facial y ocular para soldador");
                subTasks.add("Protección endaural o de copa");
                break;
            case "Inspeccionar plataformas":
                subTasks.add("Superficie antideslizante");
                subTasks.add("Cabo de vida");
                subTasks.add("Control velocidad de viento");
                subTasks.add("Permiso de trabajo");
                subTasks.add("Equipo de medición de contaminantes atmosféricos");
                break;
            case "Control de válvulas":
                subTasks.add("Control de nivel de fluidos");
                subTasks.add("Control de presión");
                subTasks.add("Apertura y cierre");
                subTasks.add("Pasaje a tanque superior");
                subTasks.add("Llenado manual");
                break;
            case "Control de máquinas":
                subTasks.add("Protecciones en partes móviles");
                subTasks.add("Estado de los cables de la máquina");
                subTasks.add("Protección anticorte");
                subTasks.add("Capacitación de uso");
                subTasks.add("Listado de último control");
                break;
            case "Trabajo en altura":
                subTasks.add("Examen de salud anual físico psiquíco");
                subTasks.add("Arnés de seguridad");
                subTasks.add("Estado apto de eslingas");
                subTasks.add("Control de todos los accesorios de izaje");
                subTasks.add("Participó en simulacros de rescate y emergencia");
                break;
            case "Demolición y excavación":
                subTasks.add("Examen de salud anual físico psiquíco");
                subTasks.add("Conoce los planos de caños de gas y luz");
                subTasks.add("Trabaja a distancia de vehículos de excavación");
                subTasks.add("Cumple con las características de submuración");
                subTasks.add("Trabaja con uno o más compañeros");
                break;
            case "Residuos especiales":
                subTasks.add("Tiene capacitación para el manejo de residuos especiales");
                subTasks.add("Tiene capacitación en enfermedades por residuos biológicos");
                subTasks.add("El depósito se encuentra señalizado y con acceso restrigido");
                subTasks.add("Cumple con el orden y limpieza el sector de residuos");
                subTasks.add("Realiza la separación de residuos especiales en bolsas rojas");
                break;
            case "Movimiento de pacientes":
                subTasks.add("Tiene capacitación en movimientos de pacientes");
                subTasks.add("Las camas poseen características para modificar la altura");
                subTasks.add("Durante el movimiento de pacientes trabaja con otra persona");
                subTasks.add("Realizan controles de salud de su columna vertebral ");
                break;
            case "Uso de la voz":
                subTasks.add("Tiene capacitación en ejercicios de modulación de la voz");
                subTasks.add("Se le seca la garganta durante la jornada laboral");
                subTasks.add("Habitualmente carraspea o tose");
                subTasks.add("Mantiene su tono de voz durante toda la jornada");
                subTasks.add("Trabaja en escuela primaria");
                break;
            case "Puesto de trabajo":
                subTasks.add("Siente que la iluminación es suficiente");
                subTasks.add("Su escritorio tiene luz natural");
                subTasks.add("Su pantalla de visualización de datos refleja la luminarias");
                subTasks.add("Posee ventilación artificial");
                subTasks.add("Posee ventilación natural");
                subTasks.add("Puede mantener una conversación frente a su compañero de trabajo sin gritar");
                subTasks.add("La base o cruz de la silla tiene 5 patas");
                subTasks.add("El respaldo de la silla es ajustable");
                subTasks.add("Posee apoya brazos");
                break;
            case "Controles de salud":
                subTasks.add("Ha visitado en los últimos 3 años a un fonoaudiólogo");
                subTasks.add("Ha visitado en los últimos 3 años a un otorrinolaringólogo");
                subTasks.add("Tiene diagnóstico de disfonía o nódulos vocales");
                subTasks.add("Ha sido operado de las cuerdas vocales");
                break;
            case "Pausas activas":
                subTasks.add("Tiene capacitación en pausas activas");
                subTasks.add("Realiza ejercicios de entrada en calor");
                subTasks.add("Realiza ejercicios de pausa activa para miembros superiores");
                subTasks.add("Realiza ejercicios de pausa activa para miembros inferiores");
                subTasks.add("Realiza ejercicios de pausa activa para cuello y espalda");
                break;
            case "Inspeccionar vehículo":
                subTasks.add("Posee registro de conducir para el tipo de vehículo que maneja");
                subTasks.add("Tiene autorización del empleador para uso de vehículos en el establecimiento");
                subTasks.add("El caño de escape tiene arrestallama");
                subTasks.add("Tiene una señalética que indica el último control del vehículo");
                subTasks.add("Tiene conocimientos de mecánica ligera");
                break;

                // Agrega más casos para otras categorías si es necesario
            default:
                // En caso de no coincidir con ninguna categoría específica
                subTasks.add("Contactar al administrador");
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