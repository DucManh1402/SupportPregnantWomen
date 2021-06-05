package com.example.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.Note;
import com.example.test.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder >{

    private List<Note> notes;
    public NoteAdapter(List<Note> notes)
    {
        this.notes = notes;
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
        holder.setNote(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
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
        void setNote(Note note){
            textTilte.setText(note.getContent());
            textDateTime.setText(note.getDate_diary());
        }
    }
}
