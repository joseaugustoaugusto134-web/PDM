package com.example.tungtungtungsabido;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int clicksPerSecond = 0;
    int record = 0;

    TextView textView;
    TextView textCount;
    TextView textRecorde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button2 = findViewById(R.id.button2);
        Button btnReset = findViewById(R.id.btnReset);

        textView = findViewById(R.id.textView);
        textCount = findViewById(R.id.textCount);
        textRecorde = findViewById(R.id.textRecorde);

        button2.setOnClickListener(view -> {
            count++;
            clicksPerSecond++;
            textView.setText(String.valueOf(count));
        });

        btnReset.setOnClickListener(view -> {
            count = 0;
            clicksPerSecond = 0;
            textView.setText("0");
            textCount.setText("0 Cliques/s");
            record = 0;
            textRecorde.setText("Recorde: 0 Cliques/s");

        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (clicksPerSecond > record) {
                        record = clicksPerSecond;
                        textRecorde.setText("Recorde: " + record + " Cliques/s");
                    }
                    textCount.setText(clicksPerSecond + " Cliques/s");
                    clicksPerSecond = 0;
                });
            }
        }, 1000, 1000);
    }
}