package com.example.unicornstore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class ItemActivity extends AppCompatActivity {

    /*Button postBtn;
    EditText username,pName,phoneNumber,location,pCondition,price;
    DatabaseReference databaseReference;
    StorageReference postImageRef;
    ImageView uploadImage;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int REQUEST_CODE=101;
    ImageView addImagePost;*/

    ImageView addImagePost;
    Button sendImagePost,back;
    EditText username,pName,phoneNumber,location,pCondition,price;
    private static final int REQUEST_CODE=101;
    Uri imageuri;
    DatabaseReference postRef;
    ProgressDialog mLoadingBar;
    StorageReference postImageRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle("Include Details");
        addImagePost=findViewById(R.id.addImagePost);
        sendImagePost=findViewById(R.id.sendImagePost);
        username = findViewById(R.id.username);
        pName = findViewById(R.id.pName);
        phoneNumber = findViewById(R.id.phoneNumber);
        location = findViewById(R.id.location);
        pCondition = findViewById(R.id.pCondition);
        price = findViewById(R.id.price);
        back = (Button) findViewById(R.id.button12);
        mLoadingBar=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        postRef=FirebaseDatabase.getInstance().getReference().child("Advertisements");
        postImageRef=FirebaseStorage.getInstance().getReference().child("PostImages");


        sendImagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPost();
            }
        });
/*
        postBtn = findViewById(R.id.postBtn);
        postBtn.setBackgroundColor(getResources().getColor(R.color.black));
        username = findViewById(R.id.username);
        pName = findViewById(R.id.pName);
        phoneNumber = findViewById(R.id.phoneNumber);
        location = findViewById(R.id.location);
        pCondition = findViewById(R.id.pCondition);
        price = findViewById(R.id.price);
        uploadImage = findViewById(R.id.uploadImage);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Advertisement");

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertPostdata();
                addPost();
                //uploadPicture();
                Intent intent = new Intent(ItemActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePictures();
            }
        });*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BuyorSellActivity.class);
                startActivity(i);
            }
        });
        addImagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        choosePictures();

            }
        });



;
    }

    private void choosePictures() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode ==RESULT_OK && data!=null && data.getData()!=null){

            imageuri = data.getData();
            addImagePost.setImageURI(imageuri);
            // uploadPicture();



        }
    }

    private void AddPost() {

        String Name = username.getText().toString();
        String Product = pName.getText().toString();
        String Contact = phoneNumber.getText().toString();
        String Place = location.getText().toString();
        String Condition = pCondition.getText().toString();
        String Price = price.getText().toString();

        mLoadingBar.setTitle("Adding Post");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();
        postImageRef.child(mUser.getUid()).putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful())
                {
                    postImageRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {




                            HashMap hashMap = new HashMap();
                            hashMap.put("Price",Price);
                            hashMap.put("postImageUrl",uri.toString());
                            hashMap.put("Product",Product);
                            hashMap.put("username",Name);
                            hashMap.put("Condition",Condition);
                            hashMap.put("Phone",Contact);
                            hashMap.put("Location",Place);

                            postRef.child(mUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful())
                                    {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(ItemActivity.this, "Post Added", Toast.LENGTH_SHORT).show();
                                        addImagePost.setImageResource(R.drawable.abh);
                                        username.setText("");
                                        pName.setText("");
                                        phoneNumber.setText("");
                                        location.setText("");
                                        pCondition.setText("");
                                        price.setText("");
                                        Intent intent = new Intent(ItemActivity.this,HomeActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(ItemActivity.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });






                        }
                    });
                }

                else
                {
                    mLoadingBar.dismiss();
                    Toast.makeText(ItemActivity.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}


















































































































    /*

    private void choosePictures() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode ==RESULT_OK && data!=null && data.getData()!=null){

            imageUri = data.getData();
            uploadImage.setImageURI(imageUri);
            // uploadPicture();



        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomkey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomkey);

        riversRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();

            }
        });
        riversRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();

                Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* private void insertPostdata() {

        String Name = username.getText().toString();
        String Product = pName.getText().toString();
        String Contact = phoneNumber.getText().toString();
        String Place = location.getText().toString();
        String Condition = pCondition.getText().toString();
        String Price = price.getText().toString();



        Post Post = new Post(Name,Product,Contact,Place,Condition,Price);
        databaseReference.push().setValue(Post);
        Toast.makeText(ItemActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ItemActivity.this,uploadImage.class);
        startActivity(intent);
        finish();

    }*/
