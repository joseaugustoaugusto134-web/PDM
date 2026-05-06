package br.edu.ifsul.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityExibirForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_exibir_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvNome = findViewById(R.id.textView);
        TextView tvEmail = findViewById(R.id.textView2);
        TextView tvTelefone = findViewById(R.id.textView3);
        TextView tvEndereco = findViewById(R.id.textView4);

        Intent it = getIntent();

        tvNome.setText(it.getStringExtra("nome"));
        tvEmail.setText(it.getStringExtra("email"));
        tvTelefone.setText(it.getStringExtra("telefone"));
        tvEndereco.setText(it.getStringExtra("endereco"));



    }



    public void fecharTela(View v){
        finish();
    }
}