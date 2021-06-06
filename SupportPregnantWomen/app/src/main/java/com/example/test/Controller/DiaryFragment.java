package com.example.test.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;
import android.widget.ImageView;

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

    public static final int REQUEST_CODE_AND_NOTE =1;
    ImageView imageViewAddDiary,imageViewBack;
    private RecyclerView recyclerView;
    private List<Diary> diaryList;
    private NoteAdapter noteAdapter;

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

        diaryList = new ArrayList<>();
        getNodes();
        noteAdapter = new NoteAdapter(diaryList);
        recyclerView.setAdapter(noteAdapter);
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

        Call<List<Diary>> call = nguonApi.apiService.getDiaries(map);
        call.enqueue(new Callback<List<Diary>>() {

            @Override
            public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {
                if (response.isSuccessful()) {
                    diaryList = response.body();
                    noteAdapter.notifyItemChanged(0);
                    recyclerView.smoothScrollToPosition(0);
                }else {}
            }

            @Override
            public void onFailure(Call<List<Diary>> call, Throwable t) {

            }
        });
//                         @Override
//                         public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {
//                             try {
//
//                                 //pegando os dados
//                                 List<Diary> listNotes = response.body();
//
//                                 //retona os dados
//                                 for (Diary c : listNotes) {
//                                     diaryList.add(c);
//                                 }
//
//                             } catch (Exception e) {
//                                 e.printStackTrace();
//                             }
//                         }
//                         @Override
//                         public void onFailure(Call<List<Diary>> call, Throwable t) {
//
//                         }
//                     }
//                );
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_AND_NOTE && requestCode ==RESULT_OK){
            getNodes();
        }
    }
}