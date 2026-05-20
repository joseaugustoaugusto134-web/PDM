package br.edu.ifsul.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityFormulario extends AppCompatActivity {

    private EditText etNome, etEmail, etTelefone, etEndereco;
    private RadioGroup rgSexo;

    // declarar objetos para interagir com a tela
    private Spinner spinnerEstadoCivil;
    // declarar adaptador para adicionar valores Spinner
    private ArrayAdapter<String> arrayAdapterSpinner;
    // declarar objetos para com os valores exibidos no Spinner
    private String[] estadosCivis = {"Selecione o estado civil",
            "Solteiro(a)","Casado(a)","Divorcidado(a)","Viúvo(a)","União estável"};

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
        Button btEnviar = findViewById(R.id.btEnviar);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etEndereco = findViewById(R.id.etEndereco);
        rgSexo = findViewById(R.id.rgSexo);



            // vincular objetos com elementos XML da tela
            spinnerEstadoCivil = (Spinner) findViewById(R.id.spnEstadoCivil);

            // ArrayAdapter para o Spinner
            arrayAdapterSpinner = new ArrayAdapter<>(
                    getApplicationContext(),   // 1 - Contexto que executará o Adapter
                    android.R.layout.simple_list_item_1,  // 2 - Linha do Spinner
                    estadosCivis);             // 3 - Valores a serem exibidos
            // vincular spinner com adaptador
            spinnerEstadoCivil.setAdapter(arrayAdapterSpinner);


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivityExibirForm.class);

                it.putExtra("nome", etNome.getText().toString());
                it.putExtra("email", etEmail.getText().toString());
                it.putExtra("telefone", etTelefone.getText().toString());
                it.putExtra("endereco", etEndereco.getText().toString());

                String sSexo = "PND";
                if(rgSexo.getCheckedRadioButtonId()==R.id.rbF){
                    sSexo = "F";
                } else if (rgSexo.getCheckedRadioButtonId()==R.id.rbM){
                    sSexo = "M";
                }
                it.putExtra("sexo", sSexo);

                String valorSelecionado = spinnerEstadoCivil.getSelectedItem().toString();

                it.putExtra("estadoCivil", valorSelecionado);

                startActivity(it);
            }
        });
    }
}