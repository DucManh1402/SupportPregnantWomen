package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mtextViewDangKi;
    private TextView mtextViewQuenMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtextViewDangKi = (TextView) findViewById(R.id.textViewDangKi);
        mtextViewDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DangKiActivity.class);
                startActivity(intent);
            }
        });
        mtextViewQuenMatKhau = (TextView) findViewById(R.id.textViewQuenMatKhau);
        mtextViewQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });
    }
}