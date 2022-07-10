package com.example.unicornstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class splashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");



        Runnable runnable = new Runnable() {
            @Override
            public void run() {






                Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();


                /*FirebaseAuth auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null) {
                    // already signed in
                    Intent intent = new Intent(splashActivity.this,BuyorSellActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    // not signed in
                    Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }*/




            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable,5000);



    }
}