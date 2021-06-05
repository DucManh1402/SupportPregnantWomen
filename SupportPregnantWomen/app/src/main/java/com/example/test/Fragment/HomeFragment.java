package com.example.test.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.test.Adapter.Adapter_week;
import com.example.test.Model.Btt_week_home;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.User;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    //
    RecyclerView mList;
    List<Btt_week_home> btt_list;
    TextView bttListweek, tvCannang, tvChieuCao, tvTuant;
    public static int tuoi;
    int noTuanTuoi;
    NguonApi nguonApi = new NguonApi();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        nguonApi.nguon();
        //Anhxa
        Anhxa(view);
        //
        btt_list = new ArrayList<>();
        tinhtuantuoi();
        //
        for(int i = 1; i<43;i++) {
            if(i==tuoi ) {
                btt_list.add(new Btt_week_home(String.valueOf(i),R.drawable.backgroup_circle));
            }else {
                btt_list.add(new Btt_week_home(String.valueOf(i)));
            }

        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList.setLayoutManager(manager);

        Adapter_week adapter = new Adapter_week(getContext(),btt_list);
        mList.setAdapter(adapter);
        return view;

    }

    private void tinhtuantuoi(){
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", GlobalsUser.getGlobalUserId());
        //lấy ngày tkcc
        Call<User> call = nguonApi.apiService.getUser(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    Date ngaytkcc = response.body().getThoi_ki_cuoi_cung();
                    final Calendar c = Calendar.getInstance();
                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(ngaytkcc);
                    long noweek = ((c.getTime().getTime()- calendar.getTime().getTime()) / (24 * 3600 * 1000)) /7;
                    noTuanTuoi = (int) noweek;
                    tuoi = noTuanTuoi;
                    tvTuant.setText(String.valueOf(noTuanTuoi));
                    //
                    if(noTuanTuoi<2) {
                        double tinh = noTuanTuoi * 0.1;
                        tvCannang.setText("Chưa đến "+tinh + "g");
                        tvChieuCao.setText("Chưa đến "+tinh + "cm");
                    }else{
                        double tinh = noTuanTuoi * 0.1;
                        tvCannang.setText(tinh + "g");
                        tvChieuCao.setText(tinh + "cm");
                    }
                }else if (response.code() == 404) {
                    Toast.makeText(getContext(), "Lỗi lấy thông tin ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Anhxa(View view){
        mList = view.findViewById(R.id.list_week);
        bttListweek = view.findViewById(R.id.btt_week_home);
        tvCannang = view.findViewById(R.id.tv_cn);
        tvChieuCao = view.findViewById(R.id.tv_cc);
        tvTuant = view.findViewById(R.id.tv_tuantuoi);

    }

}