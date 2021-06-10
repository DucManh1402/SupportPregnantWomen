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

import com.example.test.Adapter.NoteAdapter;
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

public class EditDiary extends AppCompatActivity {
    private EditText inputTitleedit, inputSubedit, inputcontentedit;
    private TextView textDateTimeedit;

    NguonApi nguonApi = new NguonApi();

    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        nguonApi.nguon();

        ImageView ivSaveedit = findViewById(R.id.imageSaveedit);
        ImageView ivBackedit = findViewById(R.id.imageBackedit);
        inputTitleedit= findViewById(R.id.editTitle);
        inputSubedit = findViewById(R.id.editSubtitle);
        inputcontentedit = findViewById(R.id.editNote);
        textDateTimeedit = findViewById(R.id.texDateTimeedit);

        hashsetNote();

        ivBackedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivSaveedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasheditNote();
            }
        });
    }
    private void hasheditNote(){
        textDateTimeedit.setText(
                new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                        .format(new Date())
        );

        testeditnote();

        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("user_id", GlobalsUser.getGlobalUserId());
        map1.put("diary_id", noteAdapter.diaryId);
        map1.put("title", inputTitleedit.getText().toString());
        map1.put("subtitle", inputSubedit.getText().toString());
        map1.put("content", inputcontentedit.getText().toString());
        map1.put("date_diary", textDateTimeedit.getText().toString());
        Call<Diary> call1 = nguonApi.apiService.UpdateDiary(map1);

        call1.enqueue(new Callback<Diary>() {
            @Override
            public void onResponse(Call<Diary> call, Response<Diary> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(EditDiary.this, "Lưu thông tin nhật kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(EditDiary.this, "Lỗi lưu thông tin(404) ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Diary> call, Throwable t) {
                Toast.makeText(EditDiary.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void hashsetNote(){
        inputTitleedit.setText(noteAdapter.titleAdapter);
        inputSubedit.setText(noteAdapter.subAdapter);
        inputcontentedit.setText(noteAdapter.contentAdapter);
        textDateTimeedit.setText(noteAdapter.datetime);

    }
    private void testeditnote(){
        if(inputTitleedit.getText().toString().trim().isEmpty()){
            Toast.makeText(EditDiary.this, "Không để trống chủ đề!!!", Toast.LENGTH_SHORT).show();
            return;
        }else if(inputSubedit.getText().toString().trim().isEmpty() && inputcontentedit.getText().toString().trim().isEmpty())
        {
            Toast.makeText(EditDiary.this, "Không để trống note!!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
