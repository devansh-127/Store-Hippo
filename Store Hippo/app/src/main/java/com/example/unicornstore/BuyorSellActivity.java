package com.example.unicornstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BuyorSellActivity extends AppCompatActivity {

    ImageView buy,sell;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyor_sell);
        getSupportActionBar().hide();
        buy=findViewById(R.id.buy);
        sell=findViewById(R.id.sell);
        logout = (Button) findViewById(R.id.button);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyorSellActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyorSellActivity.this,ItemActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                Toast.makeText(BuyorSellActivity.this, "Logout successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }
}