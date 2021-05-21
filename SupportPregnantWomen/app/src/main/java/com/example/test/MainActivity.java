package com.example.test;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.test.Controller.DangKiActivity;
import com.example.test.Controller.TrangChuActivity;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.User;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    protected Button btnLogin;
    protected EditText edtEmail, edtPass;
    protected TextView textViewDangKi;
    NguonApi nguonApi = new NguonApi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nguonApi.nguon();
        findViewById(R.id.bttDangNhap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginDialog();
            }
        });
        findViewById(R.id.textViewDangKi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DangKiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLoginDialog() {
        AnhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", edtEmail.getText().toString());
                map.put("password", edtPass.getText().toString());

                Call<User> call = nguonApi.apiService.executeLogin(map);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            GlobalsUser.setGlobalUserId(response.body().getUser_id());

                            Intent intent=new Intent(MainActivity.this, TrangChuActivity.class);
                            startActivity(intent);
                        }
                        else if (response.code() == 404) {
                            Toast.makeText(MainActivity.this, "Lỗi đăng nhập(404)", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    
                });
            }
        });

    }

    private void AnhXa() {
//        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setView(view).show();

        btnLogin = findViewById(R.id.bttDangNhap);
        textViewDangKi = findViewById(R.id.textViewDangKi);
        edtEmail = findViewById(R.id.editGmail);
        edtPass = findViewById(R.id.editPass);
    }
}