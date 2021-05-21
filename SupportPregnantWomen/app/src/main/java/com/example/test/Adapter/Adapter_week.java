package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Fragment.HomeFragment;
import com.example.test.Model.Btt_week_home;
import com.example.test.R;

import java.util.List;

public class Adapter_week extends RecyclerView.Adapter<Adapter_week.MyViewHolder> {
    private Context context;
    private List<Btt_week_home> apps;

    public Adapter_week(Context context, List<Btt_week_home> apps) {
        this.context = context;
        this.apps = apps;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button mbtt_week_home;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mbtt_week_home = itemView.findViewById(R.id.btt_week_home);
        }

    }
    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_list_week_home,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter_week.MyViewHolder holder, int position) {
        Btt_week_home app =apps.get(position);
        holder.mbtt_week_home.setText(app.getBtt_week().getText());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


}
