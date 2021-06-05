package com.example.test.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.MainActivity;
import com.example.test.Model.GlobalsUser;
import com.example.test.Model.User;
import com.example.test.NguonApi;
import com.example.test.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTin2Activity extends AppCompatActivity {

    NumberPicker np_daychuki;

    Button bttTiepTuc;
    Button btnspnthoikycuoicung;
    private int mYear, mMonth, mDay;
    Button btnspnngaylaodong;
    private int mYear1, mMonth1, mDay1;
    Button btnspnthoikithainghen;
    Button btnspnngaythuthai;
    private int mYear2, mMonth2, mDay2;

    public Dialog thainghenDialog;
    Button btnChonXong;
    NumberPicker np_week,np_day;

    NguonApi nguonApi = new NguonApi();


    Date date_thoi_ki_cuoi_cung,date_ngay_lao_dong,date_ngay_thu_thai;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtin_2);
        nguonApi.nguon();
        //
        AnhXa();
        //
        Chukikinh();
        //
        chon_ngay_tkcc();
        //
        tkThainghen();
        //
        findViewById(R.id.bttTiepTucTrangThongTin2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hand();
            }
        });
    }
    private void hand(){

        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", GlobalsUser.getGlobalUserId());
        //        map.put("thoi_ki_cuoi_cung",String.valueOf(date_thoi_ki_cuoi_cung));
//        map.put("ngay_lao_dong",String.valueOf(date_ngay_lao_dong));
//        map.put("ngay_thu_thai",String.valueOf(date_ngay_thu_thai));
        map.put("thoi_ki_cuoi_cung",btnspnthoikycuoicung.getText().toString());
        map.put("ngay_lao_dong",btnspnngaylaodong.getText().toString());
        map.put("ngay_thu_thai",btnspnngaythuthai.getText().toString());
        map.put("chu_ki_kinh", String.valueOf(np_daychuki.getValue()));
        map.put("thoi_ki_thai_nghen",btnspnthoikithainghen.getText().toString());
        map.put("can_nang","1");
        map.put("chieu_cao","1");
        Call<User> call = nguonApi.apiService.UpdateUser(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.code() == 200){
                    Toast.makeText(ThongTin2Activity.this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                    GlobalsUser.setNumber_Co(1);
                    Intent intent = new Intent(ThongTin2Activity.this, TrangChuActivity.class);
                    startActivity(intent);
                }
                else if (response.code() == 404) {
                    Toast.makeText(ThongTin2Activity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ThongTin2Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

public void chon_ngay_tkcc()
    {
        final Calendar c1 = Calendar.getInstance();
        final Calendar c2 = Calendar.getInstance();
        final Calendar c3 = Calendar.getInstance();
        final Calendar c4 = Calendar.getInstance();

        //chọn plan day
        btnspnthoikycuoicung.setOnClickListener(v -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btnspnthoikycuoicung.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            Date date = Date.valueOf(year+"-"+ (month + 1)+"-"+dayOfMonth);
//                            date_thoi_ki_cuoi_cung=date;
                            //
                            c1.setTime(date);
                            c2.setTime(date);
                            c3.setTime(date);
                            c1.add(Calendar.DATE, 280);
                            btnspnngaylaodong.setText(c1.get(Calendar.YEAR)+"-"+c1.get(Calendar.MONTH)+"-"+ c1.get(Calendar.DAY_OF_MONTH));
//                            date_ngay_lao_dong = Date.valueOf(c1.get(Calendar.YEAR)+"-"+c1.get(Calendar.MONTH)+"-"+ c1.get(Calendar.DAY_OF_MONTH));

                            c2.add(Calendar.DATE, 14);
                            btnspnngaythuthai.setText(c2.get(Calendar.YEAR)+"-"+c2.get(Calendar.MONTH)+"-"+ c2.get(Calendar.DAY_OF_MONTH));
//                            date_ngay_thu_thai = Date.valueOf(c2.get(Calendar.YEAR)+"-"+c2.get(Calendar.MONTH)+"-"+ c2.get(Calendar.DAY_OF_MONTH));

                            long noWeek = ((c4.getTime().getTime()- c3.getTime().getTime()) / (24 * 3600 * 1000)) /7;
                            long noDay = ((c4.getTime().getTime()- c3.getTime().getTime()) / (24 * 3600 * 1000)) %7;

                            btnspnthoikithainghen.setText("Tuần:"+ noWeek+", trong ngày:"+noDay);
                            //
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });
        //chọn day lao dong
        btnspnngaylaodong.setOnClickListener(v -> {
            // Get Current Date
            mYear1 = c1.get(Calendar.YEAR);
            mMonth1 = c1.get(Calendar.MONTH);
            mDay1 = c1.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btnspnngaylaodong.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            Date date = Date.valueOf(year+"-"+ (month + 1)+"-"+dayOfMonth);
                        }
                    }, mYear1, mMonth1, mDay1);
            datePickerDialog.show();
        });
        //chọn day thu thai
        btnspnngaythuthai.setOnClickListener(v -> {
            mYear2 = c2.get(Calendar.YEAR);
            mMonth2 = c2.get(Calendar.MONTH);
            mDay2 = c2.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            btnspnngaythuthai.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            Date date = Date.valueOf(year+"-"+ (month + 1)+"-"+dayOfMonth);
                        }
                    }, mYear2, mMonth2, mDay2);
            datePickerDialog.show();
        });
    }
    public  void tkThainghen()
    {
        thainghenDialog = new Dialog(this);
        thainghenDialog.setContentView(R.layout.layout_dialog_week_day);
        thainghenDialog.setCancelable(false);

        np_week = thainghenDialog.findViewById(R.id.NP_week);
        np_day = thainghenDialog.findViewById(R.id.NP_day);
        btnChonXong = thainghenDialog.findViewById(R.id.bttChonXong);
        np_week.setMinValue(0);
        np_week.setMaxValue(40);
        np_day.setMinValue(0);
        np_day.setMaxValue(6);
        btnChonXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnspnthoikithainghen.setText("Tuần:"+ np_week.getValue()+", trong ngày:"+np_day.getValue());
                thainghenDialog.dismiss();
            }
        });
        btnspnthoikithainghen.setOnClickListener(v -> {
            thainghenDialog.show();
        });
    }
    public boolean test(){

        if (btnspnthoikithainghen.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Hãy nhập thời kì thai nghén", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void Chukikinh(){
        np_daychuki.setMinValue(25);
        np_daychuki.setMaxValue(40);
    }
    private void AnhXa() {

        bttTiepTuc = findViewById(R.id.bttTiepTucTrangThongTin1);
        np_daychuki  = findViewById(R.id.npdaychuki);
        btnspnthoikycuoicung = findViewById(R.id.btnspnthoikycuoicung);
        btnspnngaylaodong = findViewById(R.id.btnspnngaylaodong);
        btnspnthoikithainghen = findViewById(R.id.btnspntkthainghen);
        btnspnngaythuthai = findViewById(R.id.btnspnngaythuthai);
    }


//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        int selected = Integer.parseInt(parent.getItemAtPosition(position).toString());
//        chukiKinh = selected;
//        Toast.makeText(this, selected,
//                Toast.LENGTH_LONG)
//                .show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
