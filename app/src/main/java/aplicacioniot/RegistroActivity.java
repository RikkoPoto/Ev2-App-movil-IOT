package aplicacioniot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import aplicacioniot.datos.Conexion;

public class RegistroActivity extends AppCompatActivity {
    EditText etNuevoUsuario, etNuevaContrasena;
    Button btnRegistrar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNuevoUsuario = findViewById(R.id.etNuevoUsuario);
        etNuevaContrasena = findViewById(R.id.etNuevaContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        String usuario = etNuevoUsuario.getText().toString().trim();
        String password = etNuevaContrasena.getText().toString().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String encodedUsuario = URLEncoder.encode(usuario, StandardCharsets.UTF_8.toString());
            String encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.toString());
            String url = Conexion.URL_WEB_SERVICES + "ingreso.php?usuario=" + encodedUsuario + "&contrasena=" + encodedPassword;

            requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    url,
                    response -> {
                        if (response != null && response.contains("Registro insertado exitoso")) {
                            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            finish(); // Vuelve a la pantalla de Login
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), "Error de conexión con el servidor", Toast.LENGTH_SHORT).show()
            );

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            Toast.makeText(this, "Error al codificar parámetros", Toast.LENGTH_SHORT).show();
        }
    }
}
