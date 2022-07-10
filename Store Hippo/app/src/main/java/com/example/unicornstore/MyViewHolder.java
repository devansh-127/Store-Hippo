package com.example.unicornstore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView productImage;
    TextView price, product, condition, place, phone;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.productImage);
        price = itemView.findViewById(R.id.price);
        product = itemView.findViewById(R.id.product);
        condition = itemView.findViewById(R.id.condition);
        place = itemView.findViewById(R.id.place);
        phone = itemView.findViewById(R.id.phone);





    }
}

