package com.machungapp.user.wearemachungers.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.machungapp.user.wearemachungers.R;
import com.machungapp.user.wearemachungers.Services.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity {
    private TextView tvInfoStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvInfoStatus = findViewById(R.id.tvInfoStatus);

        if (SaveSharedPreference.getUserName(SplashActivity.this).length() == 0) {
            tvInfoStatus.setText("Welcome ...");
            tvInfoStatus.setVisibility(View.VISIBLE);
            tvInfoStatus.setAlpha(0.0f);
            tvInfoStatus.animate().alpha(1.0f);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            tvInfoStatus.setText("Sign In ...");
            tvInfoStatus.setVisibility(View.VISIBLE);
            tvInfoStatus.setAlpha(0.0f);
            tvInfoStatus.animate().alpha(1.0f);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, UserActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }
}
