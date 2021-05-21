package com.example.test.Controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.GlobalsUser;
import com.example.test.Model.User;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_thongtin_user extends AppCompatActivity {
    EditText EditNameUser, EditEmailUser, EditSdtUser, EditDiachiUser;
    Button bttQuaylai, bttThaydoi, bttDoiMatKhau;

    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_user);
        nguonApi.nguon();
        AnhXa();
        hash_set();

    }

    private void hash_set(){
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", GlobalsUser.getGlobalUserId());

        Call<User> call = nguonApi.apiService.getUser(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    EditNameUser.setText(response.body().getName());
                    EditEmailUser.setText(response.body().getEmail());
                    EditDiachiUser.setText(response.body().getAddress());
                    EditSdtUser.setText(response.body().getPhone());
                }else if (response.code() == 404) {
                    Toast.makeText(Edit_thongtin_user.this, "Lỗi lấy thông tin(404) ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Edit_thongtin_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa(){
        EditNameUser = findViewById(R.id.editName_user);
        EditEmailUser = findViewById(R.id.editEmail_user);
        EditSdtUser = findViewById(R.id.editSdt_user);
        EditDiachiUser = findViewById(R.id.editDiachi_user);
        bttDoiMatKhau = findViewById(R.id.bttDoiPass);
        bttQuaylai = findViewById(R.id.bttQuayLai);
        bttThaydoi = findViewById(R.id.bttThayDoi);
    }
}
