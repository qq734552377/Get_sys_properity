package com.example.lc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button btnFromJni,btnFromJava,setProperity,setProperity2;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.editText);
        btnFromJava = findViewById(R.id.fromJava);
        btnFromJni = findViewById(R.id.fromJni);
        setProperity = findViewById(R.id.setProperity);
        setProperity2 = findViewById(R.id.button2);
        tvResult = findViewById(R.id.sample_text);
        btnFromJni.setOnClickListener(this);
        btnFromJava.setOnClickListener(this);
        setProperity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SystemUtils.setSystemPropertyForNative("sys.usb.config","none");
                SystemUtils.setSystemPropertyForJava("sys.usb.config","none");
            }
        });
        setProperity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SystemUtils.setSystemPropertyForNative("sys.usb.config","printer,acm,hid,adb");
                SystemUtils.setSystemPropertyForJava("sys.usb.config","printer,acm,hid,adb");
            }
        });
        Log.d("demo",SystemUtils.getNativeString());
    }

    @Override
    public void onClick(View v) {
        String key = mEditText.getText().toString().intern();
        if(TextUtils.isEmpty(key)){
            Toast.makeText(this,"key is null!",Toast.LENGTH_LONG).show();
            return;
        }
        String value = "";
        if(v == btnFromJava){
            value = SystemUtils.getSystemPropertyForJava(key,"");
        }else {
            value = SystemUtils.getSystemPropertyForNative(key,"");
        }

        tvResult.setText(value);

    }
}
