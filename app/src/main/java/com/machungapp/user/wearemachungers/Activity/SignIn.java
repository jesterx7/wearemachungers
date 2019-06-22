package com.machungapp.user.wearemachungers.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.machungapp.user.wearemachungers.R;

public class SignIn extends AppCompatActivity {
    private ImageView imgBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        imgBackground = findViewById(R.id.imgBackground);
        Glide.with(getApplicationContext())
                .load("https://hoteldekatkampus.com/wp-content/uploads/2014/10/universitas-ma-chung.jpg")
                .crossFade()
                .into(imgBackground);
    }
}
