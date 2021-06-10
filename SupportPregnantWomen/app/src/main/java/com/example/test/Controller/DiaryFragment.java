package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.Adapter.NoteAdapter;
import com.example.test.Model.Diary;
import com.example.test.Model.GlobalsUser;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiaryFragment  extends AppCompatActivity {

    public static final int REQUEST_CODE_AND_NOTE = 1;

    ImageView imageViewAddDiary,imageViewBack;
    private RecyclerView recyclerView;
    private List<Diary> diaryList;
    private NoteAdapter noteAdapter;

    private  int noteClickedPos= -1;

    NguonApi nguonApi = new NguonApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_diary);

        nguonApi.nguon();

        Anhxa();
        //
        imageViewAddDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(DiaryFragment.this, AddDiaryActivity.class),
                        REQUEST_CODE_AND_NOTE
                );
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );


        getNodes();


    }

    private void Anhxa()
    {
        imageViewAddDiary = findViewById(R.id.imageAddDiaryMain);
        imageViewBack = findViewById(R.id.imageBackDiary);
        recyclerView = findViewById(R.id.noteRecycler);
    }

    private  void getNodes(){
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", GlobalsUser.getGlobalUserId());

        Call<Diary> call = nguonApi.apiService.getDiaries(map);
        call.enqueue(new Callback<Diary>() {

            @Override
            public void onResponse(Call<Diary> call, Response<Diary> response) {
                if (response.isSuccessful()) {
//                                 Toast.makeText(DiaryFragment.this, "Hiện DS nhật kí thành công", Toast.LENGTH_SHORT).show();
                    diaryList = new ArrayList<>();
                    diaryList = response.body().getList();
                    noteAdapter = new NoteAdapter(diaryList, DiaryFragment.this);
                    recyclerView.setAdapter(noteAdapter);
                    recyclerView.smoothScrollToPosition(0);

                    EditText inputSearch = findViewById(R.id.inputsearch);
                    inputSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            noteAdapter.cancerTimer();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(diaryList.size()!=0){
                                noteAdapter.searchDiary(s.toString(),diaryList);
                            }
                        }
                    });
                } else {
                    Toast.makeText(DiaryFragment.this, "Hiện DS nhật kí thất bại", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Diary> call, Throwable t) {
                Toast.makeText(DiaryFragment.this, "Lỗi tạo nhật kí", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_AND_NOTE && resultCode ==RESULT_OK){
            getNodes();
        }
    }


}