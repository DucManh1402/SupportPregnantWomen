package com.example.test.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Controller.DiaryFragment;
import com.example.test.Controller.EditBabyActivity;
import com.example.test.Controller.Edit_thongtin_user;
import com.example.test.MainActivity;
import com.example.test.Model.Babies;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.User;
import com.example.test.NguonApi;
import com.example.test.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountKHFragment extends Fragment {
    TextView textView_name_user,textView_name_baby;
    Button bttDangxuat;
    NguonApi nguonApi = new NguonApi();
    ImageView imageViewThongtin,imageViewVietNK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_k_h, container, false);
        nguonApi.nguon();

        Anhxa(view);

        hash();

        bttDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        view.findViewById(R.id.tv_name_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Edit_thongtin_user.class);
               startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_name_baby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditBabyActivity.class);
                startActivity(intent);
            }
        });
        imageViewThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MenuFragment());
            }
        });

        imageViewVietNK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaryFragment.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void hash(){
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id", GlobalsUser.getGlobalUserId());
        //lấy user
        Call<User> call = nguonApi.apiService.getUser(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    textView_name_user.setText(response.body().getName());
                }else if (response.code() == 404) {
                    Toast.makeText(getContext(), "Lỗi lấy thông tin ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //lấy baby
        Call<Babies> call1 = nguonApi.apiService.getBaby(map);
        call1.enqueue(new Callback<Babies>() {
            @Override
            public void onResponse(Call<Babies> call, Response<Babies> response) {
                if(response.code() == 200) {
                    textView_name_baby.setText(response.body().getName()+"("+response.body().getGender()+")");
                }else if (response.code() == 404) {
                    Toast.makeText(getContext(), "Lỗi lấy thông tin(404) ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Babies> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Anhxa(View view)
    {
        textView_name_user = view.findViewById(R.id.tv_name_user);
        textView_name_baby = view.findViewById(R.id.tv_name_baby);
        bttDangxuat = view.findViewById(R.id.bttdangxuat);
        imageViewThongtin = view.findViewById(R.id.iv_thongtin);
        imageViewVietNK = view.findViewById(R.id.imageNhatKi);
    }
    private void loadFragment(Fragment fragment) {
        //replace
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();

    }


}