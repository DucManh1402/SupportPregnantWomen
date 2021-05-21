package com.example.test.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.test.Adapter.Adapter_week;
import com.example.test.MainActivity;
import com.example.test.Model.Btt_week_home;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment<NotificationFragment> extends Fragment {
    //
    RecyclerView mList;
    List<Btt_week_home> btt_list;
    Button mbtt_week;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


}