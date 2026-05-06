package br.edu.ifsul.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityFormulario extends AppCompatActivity {

    private EditText etNome, etEmail, etTelefone, etEndereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_formulario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btEnviar = findViewById(R.id.btInfo);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etEndereco = findViewById(R.id.etEndereco);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivityExibirForm.class);

                it.putExtra("nome", etNome.getText().toString());
                it.putExtra("email", etEmail.getText().toString());
                it.putExtra("telefone", etTelefone.getText().toString());
                it.putExtra("endereco", etEndereco.getText().toString());
                startActivity(it);
            }
        });
}}