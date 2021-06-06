package com.example.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.Diary;
import com.example.test.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder >{

    private List<Diary> diaries;
    public NoteAdapter(List<Diary> diaries)
    {
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
        holder.setNote(diaries.get(position));
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
        public NoteViewHolder(@NonNull  View itemView) {
            super(itemView);
            textTilte = itemView.findViewById(R.id.txt_title);
            textSubtitle = itemView.findViewById(R.id.txt_subtitle);
            textDateTime = itemView.findViewById(R.id.txt_datetime);
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
}
