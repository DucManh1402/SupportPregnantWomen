package com.example.test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.Nutrition;
import com.example.test.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NutVH> {

    private static final String TAG = "NutAdapter";
    List<Nutrition> nutList;

    public Adapter(List<Nutrition> nutList) {
        this.nutList = nutList;
    }

    @NonNull
    @Override
    public NutVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail, parent, false);
        return new NutVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutVH holder, int position) {
        Nutrition nutrition=nutList.get(position);
        holder.title1TextView.setText(nutrition.getTitle1());
        holder.title2TextView.setText(nutrition.getTitle2());
        holder.descriptionTextView.setText(nutrition.getDescription());

        boolean isExpanded = nutList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return nutList.size();
    }

    class NutVH extends RecyclerView.ViewHolder {

        private static final String TAG = "NutVH";

        ConstraintLayout expandableLayout;
        TextView title1TextView, title2TextView, descriptionTextView;

        public NutVH(@NonNull final View itemView) {
            super(itemView);

            title1TextView = itemView.findViewById(R.id.title1TextView);
            title2TextView = itemView.findViewById(R.id.title2TextView);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            title1TextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Nutrition nut = nutList.get(getAdapterPosition());
                    nut.setExpanded(!nut.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
