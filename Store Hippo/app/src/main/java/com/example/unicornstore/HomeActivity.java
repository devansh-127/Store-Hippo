package com.example.unicornstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicornstore.Utills.Posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity  {

   // private static final int REQUEST_CALL = 1;
    DatabaseReference postRef;
    FirebaseRecyclerOptions<Posts> options;
    FirebaseRecyclerAdapter<Posts, MyViewHolder> adapter;
    RecyclerView recyclerview;
    TextView phone;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       /* ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
       */


        phone = findViewById(R.id.phone);
        recyclerview = findViewById(R.id.rv);
        back = (Button)findViewById(R.id.button91);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        postRef = FirebaseDatabase.getInstance().getReference().child("Advertisements");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BuyorSellActivity.class);
                startActivity(i);
            }
        });
       /* copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });*/


        LoadPosts();


    }

    private void LoadPosts() {

        options = new FirebaseRecyclerOptions.Builder<Posts>().setQuery(postRef, Posts.class).build();
        adapter = new FirebaseRecyclerAdapter<Posts, MyViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Posts model) {

                holder.price.setText(model.getPrice());
                holder.product.setText(model.getProduct());
                holder.condition.setText(model.getCondition());
                holder.phone.setText(model.getPhone());
                holder.place.setText(model.getLocation());
                Picasso.get().load(model.getPostImageUrl()).into(holder.productImage);



            }


            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_post, parent, false);

                return new MyViewHolder(view);

            }
        };
        adapter.startListening();
        recyclerview.setAdapter(adapter);

    }

  /*  private void callPhone() {

        String number = phone.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(HomeActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(HomeActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}




































  /*  RecyclerView recyclerView;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, MyViewHolder> adapter;
    DatabaseReference databaseReference;

   */






















    /* databaseReference = FirebaseDatabase.getInstance().getReference().child("Advertisement");
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData();
    }

    private void LoadData() {
    }*/










      /*  options = new FirebaseRecyclerOptions.Builder<Post>().setQuery(databaseReference,Post.class).build();
        adapter=new FirebaseRecyclerAdapter<Post, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position, @NonNull Post model) {

                myViewHolder.price.setText(model.getPrice());
                myViewHolder.product.setText(model.getProduct());
                myViewHolder.condition.setText(model.getCondition());
                myViewHolder.location.setText(model.getPlace());
                myViewHolder.phone.setText(model.getContact());



            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_post,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);*/
/*
        options=new FirebaseRecyclerOptions.Builder<Post>().setQuery(databaseReference,Post.class).build();
        adapter = new FirebaseRecyclerAdapter<Post, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Post model) {
                String postKey = getRef(position).getKey();
                holder.price.setText(model.getPrice());
                holder.product.setText(model.getProduct());
                holder.condition.setText(model.getCondition());
                holder.location.setText(model.getPlace());
                holder.phone.setText(model.getContact());
               // Picasso.get().load(model.getPostImageUrl()).into(holder.postImage);



            }

            @NonNull
            @NotNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_post,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }*/
