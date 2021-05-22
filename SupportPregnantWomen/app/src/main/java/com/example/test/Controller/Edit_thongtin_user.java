package com.example.test.Controller;

import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.APIError;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.HandleError;
import com.example.test.Model.User;
import com.example.test.NguonApi;
import com.example.test.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_thongtin_user extends AppCompatActivity {
    EditText EditNameUser, EditEmailUser, EditSdtUser, EditDiachiUser;
    Button bttQuaylai, bttThaydoi, bttDoiMatKhau;

    public Dialog doiPassDialog;
    Button bttThaydoi_Pass,bttQuaylai_Pass;
    EditText EditPass_Old, EditPass_New, EditPass_ReNew;

    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_user);
        nguonApi.nguon();

        AnhXa();

        hash_set();

        Dialog_doiPass();

        findViewById(R.id.bttQuayLai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalsUser.setNumber_Co(4);
                Intent myIntent = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        findViewById(R.id.bttThayDoi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                               hash_get();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(Edit_thongtin_user.this);
                builder.setMessage("Bạn có chắc thay đổi thông tin không?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
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
    private void hash_get(){
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", GlobalsUser.getGlobalUserId());
        map.put("name",EditNameUser.getText().toString());
        map.put("email",EditEmailUser.getText().toString());
        map.put("phone",EditSdtUser.getText().toString());
        map.put("address",EditDiachiUser.getText().toString());

        Call<User> call = nguonApi.apiService.UpdateUser(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Toast.makeText(Edit_thongtin_user.this, "Thay đổi thành công ", Toast.LENGTH_SHORT).show();
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
    public  void Dialog_doiPass()
    {
        doiPassDialog = new Dialog(this);
        doiPassDialog.setContentView(R.layout.layout_dialog_pass);
        doiPassDialog.setCancelable(false);

        EditPass_Old = doiPassDialog.findViewById(R.id.editPass_cu);
        EditPass_New = doiPassDialog.findViewById(R.id.editPass_moi);
        EditPass_ReNew = doiPassDialog.findViewById(R.id.editRePass_moi);
        bttThaydoi_Pass = doiPassDialog.findViewById(R.id.bttThayDoi_Pass);
        bttQuaylai_Pass = doiPassDialog.findViewById(R.id.bttQuayLai_Pass);
        bttQuaylai_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiPassDialog.dismiss();
            }
        });
        bttDoiMatKhau.setOnClickListener(v -> {
            doiPassDialog.show();
        });
        bttThaydoi_Pass.setOnClickListener(v -> {
            if(testPass()==true) {
                DoiPass();

            }
        });
    }
    public void DoiPass(){
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", GlobalsUser.getGlobalUserId());
        map.put("password",EditPass_Old.getText().toString());
        map.put("newPassword",EditPass_New.getText().toString());

        Call<HandleError> call = nguonApi.apiService.UpdatePassUser(map);

        call.enqueue(new Callback<HandleError>() {
            @Override
            public void onResponse(Call<HandleError> call, Response<HandleError> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Edit_thongtin_user.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    APIError message = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
                    Toast.makeText(Edit_thongtin_user.this, "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HandleError> call, Throwable t) {
                Toast.makeText(Edit_thongtin_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
    public boolean testPass()
    {
        if (EditPass_New.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không để trống Mật khẩu mới", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (EditPass_ReNew.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không để trống nhập lại Mật khẩu mới", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (EditPass_Old.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Không để trống Mật khẩu cũ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!EditPass_ReNew.getText().toString().trim().equals(EditPass_New.getText().toString().trim())) {
            EditPass_ReNew.requestFocus();
            EditPass_ReNew.setError("Không khớp với mật khẩu");
            return false;
        }
        return true;
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
