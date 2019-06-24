package com.machungapp.user.wearemachungers.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.machungapp.user.wearemachungers.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignIn extends AppCompatActivity {
    private ImageView imgBackground;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private String username = "", password="";
    private Boolean logged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = "";
        password="";
        logged = false;

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        imgBackground = findViewById(R.id.imgBackground);
        Glide.with(getApplicationContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgBackground);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUsername.getText()) || TextUtils.isEmpty(edtPassword.getText())) {
                    Toast.makeText(getApplicationContext(), "Both password and username can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    username = edtUsername.getText().toString();
                    password = md5(edtPassword.getText().toString());
                    System.out.println("PASSWORD " + password);
                    loginValidation();
                }
            }
        });

    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            
            e.printStackTrace();
        }
        return "";
    }

    private void loginValidation() {
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("mahasiswa")
                .orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("nim").getValue().toString().equals(username) && data.child("password").getValue().toString().equals(password)) {
                        logged = true;
                    }
                }
                if (logged) {
                    Toast.makeText(getApplicationContext(), "Login Successfull as " + username, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password, please Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
