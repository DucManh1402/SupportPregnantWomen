package com.example.test.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test.Controller.AddDiaryActivity;
import com.example.test.R;


public class DiaryFragment extends Fragment {

    public static final int REQUEST_CODE_AND_NOTE =1;
    ImageView imageViewAddDiary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        Anhxa(view);

        imageViewAddDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getContext(), AddDiaryActivity.class),
                        REQUEST_CODE_AND_NOTE
                );
            }
        });


        return view;
    }

    private void Anhxa(View view)
    {
        imageViewAddDiary = view.findViewById(R.id.imageAddDiaryMain);
    }


}