package com.example.test.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Controller.DiaryFragment;
import com.example.test.Controller.EditDiary;
import com.example.test.Model.Diary;
import com.example.test.Model.GlobalsUser;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder >{

    public static final int REQUEST_CODE_AND_NOTE = 1;
    private Context context;
    private List<Diary> diaries;
    public static String datetime;
    public static String diaryId,titleAdapter,subAdapter,contentAdapter;

    private Timer timer;
    private List<Diary> diariessearch;
    NguonApi nguonApi = new NguonApi();
    public NoteAdapter(List<Diary> diaries,Context c)
    {
        this.context = c;
        this.diaries = diaries;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_item_diary,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull  NoteAdapter.NoteViewHolder holder, int position) {
        nguonApi.nguon();

        holder.setNote(diaries.get(position));
//
        holder.layoutDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, EditDiary.class);
                diaryId = diaries.get(position).getDiary_id();
                titleAdapter = diaries.get(position).getTitle();
                subAdapter = diaries.get(position).getSubtitle();
                contentAdapter = diaries.get(position).getContent();
                datetime = diaries.get(position).getDate_diary();
                startActivityForResult((Activity) context,intent,REQUEST_CODE_AND_NOTE,null);
            }
        });

        holder.layoutDiary.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                HashMap<String, String> map = new HashMap<>();
//                                map.put("user_id", diaries.get(position).getUser_id());
                                map.put("diary_id", diaries.get(position).getDiary_id());
                                Call<Diary> call = nguonApi.apiService.DeleteDiary(map);

                                call.enqueue(new Callback<Diary>() {
                                    @Override
                                    public void onResponse(Call<Diary> call, Response<Diary> response) {
//                                        Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                                        holder.layoutDiary.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFailure(Call<Diary> call, Throwable t) {
                                        Toast.makeText(context, "Lỗi xóa ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc muốn xóa note "+diaries.get(position).getTitle()+" không?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return diaries.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView textTilte, textSubtitle, textDateTime;
        LinearLayout layoutDiary;
        public NoteViewHolder(@NonNull  View itemView) {
            super(itemView);
            textTilte = itemView.findViewById(R.id.txt_title);
            textSubtitle = itemView.findViewById(R.id.txt_subtitle);
            textDateTime = itemView.findViewById(R.id.txt_datetime);
            layoutDiary= itemView.findViewById(R.id.layoutDiary);

        }
        void setNote(Diary diary){
            textTilte.setText(diary.getTitle());
            if(diary.getSubtitle().trim().isEmpty()){
                textSubtitle.setVisibility(View.GONE);
            }else {
                textSubtitle.setText(diary.getSubtitle());
            }
            textDateTime.setText(diary.getDate_diary());
        }
    }
    public void searchDiary(final String searchKey,final List<Diary> diaries1)
    {
        timer = new Timer();
        diaries = diaries1;
        diariessearch = diaries1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(searchKey.trim().isEmpty()){
                    diaries = diariessearch;
                }else {
                    ArrayList<Diary> temp = new ArrayList<>();
                    for(Diary diary : diariessearch)
                    {
                        if(diary.getTitle().toLowerCase().contains(searchKey.toLowerCase())
                        || diary.getSubtitle().toLowerCase().contains(searchKey.toLowerCase())
                        || diary.getContent().toLowerCase().contains(searchKey.toLowerCase())){
                        temp.add(diary);
                        }
                    }
                    diaries =temp;
                }
                new Handler(Looper.getMainLooper()) {

                }.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        },500);
    }

    public void cancerTimer(){
        if(timer != null){
            timer.cancel();
        }
    }
}
