package com.bytedance.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Log.i("MainActivity", "Create");
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PracticeActivity.this, "你好缺德！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "Start");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "Resume");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "Restart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "Destroy");
    }
}