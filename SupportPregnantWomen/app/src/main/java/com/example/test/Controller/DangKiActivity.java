package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Api.ApiService;
import com.example.test.MainActivity;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.HandleError;
import com.example.test.NguonApi;
import com.example.test.R;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.UUID;

public class DangKiActivity extends AppCompatActivity {
    private UUID uuid = UUID.randomUUID();
    protected Button btnDangKi;
    protected TextView textViewDangNhap;
    protected EditText editHoTen,edtEmail, edtPass, edtRePass;
    //
    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangki);
        //
        nguonApi.nguon();

        findViewById(R.id.bttDangKi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUpDialog();
            }
        });

    }
    private void handleSignUpDialog() {
        AnhXa();
        if(test()==true)
        {
            String user_id = uuid.randomUUID().toString();
            // Ở đây m set global được
            HashMap<String, String> map = new HashMap<>();

            map.put("user_id", user_id);
            map.put("name", editHoTen.getText().toString());
            map.put("email", edtEmail.getText().toString());
            map.put("password", edtPass.getText().toString());

            Call<Void> call = nguonApi.apiService.executeSignUp(map);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                        GlobalsUser.setGlobalUserId(user_id);

                        Intent intent = new Intent(DangKiActivity.this, ThongTinActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 400) {
                        try {
                            Toast.makeText(DangKiActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(DangKiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean test()
    {
        if (editHoTen.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập họ và tên của bạn", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edtEmail.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập email của bạn", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtPass.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập password của bạn", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtRePass.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập lại password của bạn", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!edtRePass.getText().toString().trim().equals(edtPass.getText().toString().trim())) {
            edtRePass.requestFocus();
            edtRePass.setError("Không khớp với mật khẩu");
            return false;
        }
        return true;
    }
    private void AnhXa() {
//        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setView(view).show();

        btnDangKi = findViewById(R.id.bttDangKi);
        textViewDangNhap = findViewById(R.id.textViewDangNhap);
        editHoTen =findViewById(R.id.editHoTen);
        edtEmail = findViewById(R.id.editGmail);
        edtPass = findViewById(R.id.editPass);
        edtRePass = findViewById(R.id.editRe_Pass);
    }
}
