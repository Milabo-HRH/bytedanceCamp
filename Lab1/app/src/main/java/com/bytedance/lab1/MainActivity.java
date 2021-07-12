package com.bytedance.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Switch s = findViewById(R.id.switch1);
                boolean b = s.isChecked();
                String a;
                if(!b) {
                    EditText t = findViewById(R.id.editText);
                    a = t.getText().toString();
                } else {
                    a = "xxx";
                }
                setContentView(R.layout.activity_second);
                TextView tv = findViewById(R.id.textView2);
                tv.setText("Hello, " + a + ".");

            }
        });
        Log.i("111111", "onCreate: 1111");
    }
}
