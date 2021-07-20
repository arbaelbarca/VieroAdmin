package com.cindodcindy.vieroshoesadminnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.cindodcindy.vieroshoesadminnew.view.Login;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;

import static com.cindodcindy.vieroshoesadminnew.view.utils.ExtKt.dexterPermissionMultiLocation;

public class MainActivity extends AppCompatActivity {

    private int waktu_loading = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            //setelah loading maka akan langsung berpindah ke home activity
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();

        }, waktu_loading);

        FirebaseApp.initializeApp(MainActivity.this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
    }
}