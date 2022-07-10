package com.example.unicornstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView tv;
    Button btnLogin;
    EditText inputEmail,inputPassword;
    ProgressDialog mLoadingBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        tv = findViewById(R.id.createNewAccount);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        mLoadingBar=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                // String confirmPassword = inputConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter Your Details", Toast.LENGTH_SHORT).show();
                }
                /*else if (!confirmPassword.equals(password))
                {
                    Toast.makeText(RegisterActivity.this, "Password does not Match", Toast.LENGTH_SHORT).show();
                }*/
                else {

                    loginUser(email,password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {

        mLoadingBar.setTitle("Signing In");
        mLoadingBar.setMessage("Welcome Back!!");
        mLoadingBar.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    mLoadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Sign-in Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (LoginActivity.this,BuyorSellActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    mLoadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



}