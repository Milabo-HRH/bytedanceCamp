package com.bytedance.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "Create");
        initView();
    }
    private void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, PracticeActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent implicitIntent = new Intent();
//               implicitIntent.setAction("layout.activity");
                implicitIntent.setAction(Intent.ACTION_DIAL);
                startActivity(implicitIntent);
                break;
        }
    }
};