package com.bytedance.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] i = {0};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i[0]++;
                Toast.makeText(MainActivity.this, "但你还是按了"+String.valueOf(i[0])+"次", Toast.LENGTH_SHORT).show();
            }
        });
    }
}