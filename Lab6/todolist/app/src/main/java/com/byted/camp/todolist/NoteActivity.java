package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoContract.TodoNote;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private RadioGroup radioGroup;
    private AppCompatRadioButton lowRadio;
    private final String dir = "draft.txt";
    private String mFileName;
    private boolean written = false;
    private TodoDbHelper dbHelper;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        dbHelper = new TodoDbHelper(this);
        database = dbHelper.getWritableDatabase();
        mFileName = getFilesDir()+ File.separator+dir;




        editText = findViewById(R.id.edit_text);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(mFileName);
                if (!file.exists()) {
                    try {
                        boolean isSuccess = file.createNewFile();
                        if (!isSuccess) {
                            throw new IOException("create exception error.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] bytes = new byte[1024];
                    final StringBuffer sb = new StringBuffer();
                    while (inputStream.read(bytes) != -1) {
                        sb.append(new String(bytes));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editText.setText(sb.toString());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        editText.setFocusable(true);
        editText.requestFocus();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }
        radioGroup = findViewById(R.id.radio_group);
        lowRadio = findViewById(R.id.btn_low);
        lowRadio.setChecked(true);

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(),
                        getSelectedPriority());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                written = true;
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 创建文件
                    File file = new File(mFileName);
                    if (!file.exists()) {
                        try {
                            boolean isSuccess = file.createNewFile();
                            if (!isSuccess) {
                                throw new IOException("create exception error.");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //
                    FileOutputStream outputStream = null;
                    try {
                        outputStream = new FileOutputStream(file);
                        if(!written)
                            outputStream.write((editText.getText().toString()+"\0").getBytes());

                    }  catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != outputStream) {
                                outputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        super.onDestroy();
        database.close();
        database = null;
        dbHelper.close();
        dbHelper = null;
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO: 2021/7/19 8. 这里插入数据库
        ContentValues values = new ContentValues();
        values.put(TodoContract.TodoNote.COLUMN_NAME_DATE, (new Date()).getTime());
        values.put(TodoContract.TodoNote.COLUMN_NAME_CONTENT, content);
        values.put(TodoContract.TodoNote.COLUMN_NAME_STATE, (0));
        values.put(TodoContract.TodoNote.COLUMN_NAME_PRIORITY, (priority.intValue));

        long newRowId = database.insert(TodoNote.Table_NAME, null, values);
        return true;
    }

    private Priority getSelectedPriority() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.btn_high:
                return Priority.High;
            case R.id.btn_medium:
                return Priority.Medium;
            default:
                return Priority.Low;
        }
    }
}
