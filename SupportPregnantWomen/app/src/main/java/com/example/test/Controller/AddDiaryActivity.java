package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Model.Diary;
import com.example.test.Model.GlobalsUser;
import com.example.test.NguonApi;
import com.example.test.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDiaryActivity extends AppCompatActivity {
    private EditText inputTitle, inputSub, inputcontent;
    private TextView textDateTime;

    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        nguonApi.nguon();

        ImageView ivSave = findViewById(R.id.imageSave);
        ImageView ivBack = findViewById(R.id.imageBack);
        inputTitle= findViewById(R.id.inputTitle);
        inputSub = findViewById(R.id.inputSubtitle);
        inputcontent = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.texDateTime);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                .format(new Date())
        );

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashNote();
            }
        });
    }
    private void hashNote() {
        testnote();
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", GlobalsUser.getGlobalUserId());
        map.put("title", inputTitle.getText().toString());
        map.put("subtitle", inputSub.getText().toString());
        map.put("content", inputcontent.getText().toString());
        map.put("date_diary", textDateTime.getText().toString());

        Call<Diary> call = nguonApi.apiService.addDiary(map);

        call.enqueue(new Callback<Diary>() {
            @Override
            public void onResponse(Call<Diary> call, Response<Diary> response) {
                Toast.makeText(AddDiaryActivity.this, "Tạo nhật kí mới thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onFailure(Call<Diary> call, Throwable t) {
                Toast.makeText(AddDiaryActivity.this, "Lỗi tạo nhật kí", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void testnote(){
        if(inputTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(AddDiaryActivity.this, "Không để trống chủ đề!!!", Toast.LENGTH_SHORT).show();
            return;
        }else if(inputSub.getText().toString().trim().isEmpty() && inputcontent.getText().toString().trim().isEmpty())
        {
            Toast.makeText(AddDiaryActivity.this, "Không để trống note!!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
