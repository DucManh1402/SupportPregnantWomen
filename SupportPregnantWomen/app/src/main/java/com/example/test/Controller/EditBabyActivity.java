package com.example.test.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.Babies;
import com.example.test.Model.GlobalsUser;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBabyActivity extends AppCompatActivity {
    EditText editNameBaby;
    RadioGroup rGroupSex;
    RadioButton radioBttMale;
    RadioButton radioBttFemale;
    RadioButton radioBttChuabiet;
    EditText editTinhTrangSKBaby;
    Button bttThayDoiBaby,bttQuayLaiBaby;
private String Baby_id;
    private RadioButton radioButton;

    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_baby);
        nguonApi.nguon();
        AnhXa();
        hand_set();
        findViewById(R.id.bttThayDoi_baby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                hand_get();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(EditBabyActivity.this);
                builder.setMessage("B???n c?? ch???c thay ?????i th??ng tin kh??ng?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }

        });
        findViewById(R.id.bttQuayLai_baby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalsUser.setNumber_Co(4);
                Intent myIntent = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivityForResult(myIntent, 1);
            }
        });
    }
    private void hand_set(){
        AnhXa();
        int radioG = rGroupSex.getCheckedRadioButtonId();
        radioButton = findViewById(radioG);

            HashMap<String, String> map = new HashMap<>();
            map.put("user_id", GlobalsUser.getGlobalUserId());

            Call<Babies> call = nguonApi.apiService.getBaby(map);

            call.enqueue(new Callback<Babies>(){

                @Override
                public void onResponse(Call<Babies> call, Response<Babies> response) {
                    if(response.code() == 200){
                        editNameBaby.setText(response.body().getName());
                        editTinhTrangSKBaby.setText(response.body().getTinh_trang_suc_khoe());
                        if(response.body().getGender()=="B?? trai")
                        {
                            radioBttMale.isChecked();
                        }else if(response.body().getGender()=="B?? g??i")
                        {
                            radioBttFemale.isChecked();
                        }else{
                            radioBttChuabiet.isChecked();
                        }
                        Baby_id = response.body().getBaby_id();
                    }
                    else if (response.code() == 404) {
                        Toast.makeText(EditBabyActivity.this, "L???i hi???n d??? li???u em b??", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<Babies> call, Throwable t) {
                    Toast.makeText(EditBabyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void hand_get(){
        AnhXa();
        int radioG = rGroupSex.getCheckedRadioButtonId();
        radioButton = findViewById(radioG);
        if(test()==true) {
            HashMap<String, String> map = new HashMap<>();
            map.put("user_id", GlobalsUser.getGlobalUserId());
            map.put("baby_id", Baby_id);
            map.put("name", editNameBaby.getText().toString());
            map.put("tinh_trang_suc_khoe", editTinhTrangSKBaby.getText().toString());
            map.put("gender", radioButton.getText().toString());

            Call<Babies> call = nguonApi.apiService.UpdateBaby(map);

            call.enqueue(new Callback<Babies>(){

                @Override
                public void onResponse(Call<Babies> call, Response<Babies> response) {
                    if(response.code() == 200){
                        Toast.makeText(EditBabyActivity.this, "Thay ?????i d??? li???u em b?? th??nh c??ng", Toast.LENGTH_SHORT).show();

                    }
                    else if (response.code() == 404) {
                        Toast.makeText(EditBabyActivity.this, "L???i Thay ?????i d??? li???u em b??", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<Babies> call, Throwable t) {
                    Toast.makeText(EditBabyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public boolean test(){
        if (editNameBaby.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "H??y nh???p t??n b??", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTinhTrangSKBaby.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "H??y nh???p t??nh tr???ng s???c kh???e", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!radioBttMale.isChecked() && !radioBttFemale.isChecked() && !radioBttChuabiet.isChecked()) {
            Toast.makeText(getApplicationContext(), "H??y ch???n gi???i t??nh cho b??", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void AnhXa() {
        bttThayDoiBaby = findViewById(R.id.bttThayDoi_baby);
        bttQuayLaiBaby = findViewById(R.id.bttQuayLai_baby);
        radioBttMale = findViewById(R.id.radioBttTrai);
        radioBttFemale = findViewById(R.id.radioBttGai);
        radioBttChuabiet = findViewById(R.id.radioToiChuaBiet);
        editNameBaby = findViewById(R.id.editName_baby);
        editTinhTrangSKBaby = findViewById(R.id.editTinhtrangsk_baby);
        rGroupSex = findViewById(R.id.radioGroupSex_baby);
    }
}
