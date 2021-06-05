package com.example.test.Controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.test.Fragment.AccountKHFragment;
import com.example.test.Fragment.ClockFragment;
import com.example.test.Fragment.DiaryFragment;
import com.example.test.Fragment.HomeFragment;
import com.example.test.Fragment.MenuFragment;
import com.example.test.Model.GlobalsUser;
import com.example.test.R;


public class TrangChuActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        //
        ghep();
        //add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_menu_book));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_access_time));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_account_circle));
        //

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();

                        break;
                    case 2:
                        fragment = new MenuFragment();
                        break;
                    case 3:
                        fragment = new ClockFragment();
                        break;
                    case 4:
                        fragment = new AccountKHFragment();
                        break;
                    case 5:
                        fragment = new DiaryFragment();
                        break;
                }
                //Load
                loadFragment(fragment);
            }
        });
        //set HOME
//        bottomNavigation.setCount(2,"10");
        // set home fragment initially selected
       bottomNavigation.show(GlobalsUser.getNumber_Co(),true);
//        bottomNavigation.show(2,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1)
                {
                    Toast.makeText(getApplicationContext(),"Trang chủ",Toast.LENGTH_SHORT).show();
                }
                //Display toast
                if(item.getId()==2)
                {
                    Toast.makeText(getApplicationContext(),"Thông tin cần thiết",Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==3)
                {
                    Toast.makeText(getApplicationContext(),"Bộ đếm",Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==4)
                {
                    Toast.makeText(getApplicationContext(),"Cài đặt",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1)
                {
                    Toast.makeText(getApplicationContext(),"Trang chủ",Toast.LENGTH_SHORT).show();
                }
                //Display toast
                if(item.getId()==2)
                {
                    Toast.makeText(getApplicationContext(),"Thông tin cần thiết",Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==3)
                {
                    Toast.makeText(getApplicationContext(),"Bộ đếm",Toast.LENGTH_SHORT).show();
                }
                if(item.getId()==4)
                {
                    Toast.makeText(getApplicationContext(),"Cài đặt",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        //replace
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    private void ghep(){
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }
}
