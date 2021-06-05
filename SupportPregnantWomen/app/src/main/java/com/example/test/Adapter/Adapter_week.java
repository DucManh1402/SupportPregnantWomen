package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.Fragment.HomeFragment;
import com.example.test.Model.Btt_week_home;
import com.example.test.R;

import java.util.List;

public class Adapter_week extends RecyclerView.Adapter<Adapter_week.MyViewHolder> {

    private Context context;
    private List<Btt_week_home> list_btt_week_home;

    public Adapter_week(Context context,List<Btt_week_home> list_btt_week_home) {
        this.list_btt_week_home = list_btt_week_home;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mbtt_week_home;
        ImageView iV_week_home;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            mbtt_week_home = itemView.findViewById(R.id.btt_week_home);
            iV_week_home = itemView.findViewById(R.id.image_week_home);
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


        holder.mbtt_week_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.iV_week_home.setImageResource(R.drawable.backgroup_circle);
            }
        });

        Btt_week_home btt_week_home = list_btt_week_home.get(position);
        if(btt_week_home == null){
            return;
        }
        holder.mbtt_week_home.setText(btt_week_home.getNo_week_home());
        holder.iV_week_home.setImageResource(btt_week_home.getImage_week_home());
    }

    @Override
    public int getItemCount() {
        if(list_btt_week_home != null)
        {
            return list_btt_week_home.size();
        }
        return 0;
    }


}
