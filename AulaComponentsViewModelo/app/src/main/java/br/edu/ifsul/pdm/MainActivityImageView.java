package br.edu.ifsul.pdm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivityImageView extends AppCompatActivity {
//~\AppData\Local\Android\Sdk\emulator\emulator.exe -avd Pixel_9 -dns-server 8.8.8.8 -no-snapshot-load
    private ImageView ivCircular, ivQuadrado;
    private Button btGaleria;

    private ActivityResultLauncher<Intent> tratadorDeResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_image_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivCircular = findViewById(R.id.ivCircular);
        ivQuadrado = findViewById(R.id.ivQuadrado);
        btGaleria = findViewById(R.id.button2);

        Picasso.get().load("https://i.imgur.com/DvpvklR.jpeg").into(ivCircular);
        Picasso.get().load("https://i.imgur.com/DvpvklR.jpeg").into(ivQuadrado);

        // faz um processo para tratar a resposta
        tratadorDeResposta = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() ==  RESULT_OK){
                            //Retorna os dados trazidos
                            carregaImagem(result);
                        }
                    }
                });
        // buaca a imagem na galeria
        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent takePictureIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                tratadorDeResposta.launch(takePictureIntent);

            }
        });

    }
    // função para carregar a imagem na tela
    public void carregaImagem(ActivityResult result){
        // 1. Seleciona a localização da imagem
        Uri imageUri = result.getData().getData();


        if (imageUri != null) {
            try {
                Bitmap imageBitmap;


                // 2. converte a URI para Bitmap.
                // Newer Android versions require ImageDecoder.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                    imageBitmap = ImageDecoder.decodeBitmap(source);
                } else {
                    // Fallback for older Android versions
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                }


                // 3. Exibe a imagem selecionada
                ivQuadrado.setImageBitmap(imageBitmap);


                // 4. Salvar arquivo
                File f = new File(getApplicationContext().getCacheDir(), "arquivo3.jpg");
                f.createNewFile();


                // Converte bitmap para byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();


                // Escreve os no arquivo
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}