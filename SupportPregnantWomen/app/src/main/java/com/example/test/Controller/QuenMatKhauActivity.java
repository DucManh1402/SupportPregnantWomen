package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.MainActivity;
import com.example.test.R;

public class QuenMatKhauActivity extends AppCompatActivity {
    private TextView mtextViewDangNhap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quen_mat_khau);
        mtextViewDangNhap = (TextView) findViewById(R.id.textViewDangNhap);
        mtextViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuenMatKhauActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
