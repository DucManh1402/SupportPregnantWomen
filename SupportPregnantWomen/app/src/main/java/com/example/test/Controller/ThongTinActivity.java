package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.test.Model.Babies;
import com.example.test.Model.GlobalsUser;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongTinActivity extends AppCompatActivity  {


    EditText editNameBaby;
    RadioGroup rGroupSex;
    RadioButton radioBttMale;
    RadioButton radioBttFemale;
    RadioButton radioBttChuabiet;
    EditText editTinhTrangSK;
    Button bttTiepTuc;
    private RadioButton radioButton;

    //
    NguonApi nguonApi = new NguonApi();
//

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        nguonApi.nguon();

        findViewById(R.id.bttTiepTucTrangThongTin1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hand();
            }
        });

    }
    private void hand(){
        AnhXa();
        int radioG = rGroupSex.getCheckedRadioButtonId();
        radioButton = findViewById(radioG);
        if(test()==true) {
            HashMap<String, String> map = new HashMap<>();
            map.put("user_id", GlobalsUser.getGlobalUserId());
            map.put("name", editNameBaby.getText().toString());
            map.put("tinh_trang_suc_khoe", editTinhTrangSK.getText().toString());
            map.put("gender", radioButton.getText().toString());

            Call<Babies> call = nguonApi.apiService.insertBaby(map);

            call.enqueue(new Callback<Babies>(){

                @Override
                public void onResponse(Call<Babies> call, Response<Babies> response) {
                    if(response.code() == 200){
                    Toast.makeText(ThongTinActivity.this, "Tạo dữ liệu em bé thành công", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ThongTinActivity.this, ThongTin2Activity.class);
                    startActivity(intent);
                }
                else if (response.code() == 404) {
                    Toast.makeText(ThongTinActivity.this, "Lỗi tạo em bé", Toast.LENGTH_SHORT).show();
                }
            }


                @Override
                public void onFailure(Call<Babies> call, Throwable t) {
                    Toast.makeText(ThongTinActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean test(){
        if (editNameBaby.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập tên bé", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTinhTrangSK.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập tình trạng sức khỏe", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!radioBttMale.isChecked() && !radioBttFemale.isChecked() && !radioBttChuabiet.isChecked()) {
            Toast.makeText(getApplicationContext(), "Hãy chọn giới tính cho bé", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void AnhXa() {
//        userId = findViewById(R.id.tvuserid);
        bttTiepTuc = findViewById(R.id.bttTiepTucTrangThongTin1);
        radioBttMale = findViewById(R.id.radioBttMale);
        radioBttFemale = findViewById(R.id.radioBttFemale);
        radioBttChuabiet = findViewById(R.id.radioChuaBiet);
        editNameBaby = findViewById(R.id.editNameBaby);
        editTinhTrangSK = findViewById(R.id.editTinhtrangsk);
        rGroupSex = findViewById(R.id.radioGroupSex);
    }

}
