package com.cindodcindy.vieroshoesadmin.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.cindodcindy.vieroshoesadmin.R;
import com.cindodcindy.vieroshoesadmin.view.fragment.FrChat;
import com.cindodcindy.vieroshoesadmin.view.fragment.FrGetOrder;
import com.cindodcindy.vieroshoesadmin.view.fragment.FrGetPayment;
import com.cindodcindy.vieroshoesadmin.view.fragment.FrListItem;
import com.cindodcindy.vieroshoesadmin.view.fragment.FrUploadItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_admin);

        //Menampilkan halaman Fragment yang pertama kali muncul
        getFragmentPage(new FrUploadItem());

        /*Inisialisasi BottomNavigationView beserta listenernya untuk
         *menangkap setiap kejadian saat salah satu menu item diklik
         */
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.sell_item:
                        fragment = new FrUploadItem();
                        break;

                    case R.id.home_shoes:
                        fragment = new FrListItem();
                        break;

                    case R.id.get_order:
                        fragment = new FrGetOrder();
                        break;
                    case R.id.get_payment:
                        fragment = new FrGetPayment();
                        break;

                    case R.id.chat:
                        fragment = new FrChat();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }

    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}