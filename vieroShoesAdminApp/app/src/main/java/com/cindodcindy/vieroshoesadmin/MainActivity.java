package com.cindodcindy.vieroshoesadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cindodcindy.vieroshoesadmin.view.Login;

public class MainActivity extends AppCompatActivity {

    private int waktu_loading=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();

            }
        },waktu_loading);
    }
}