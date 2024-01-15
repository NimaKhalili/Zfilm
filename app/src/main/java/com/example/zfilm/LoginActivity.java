package com.example.zfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private boolean keep = true;
    private final int DELAY = 1250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        //Do not run activity if the user is registered
        if (checkUserData())
            login();

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        EditText username = findViewById(R.id.editText_login_username);
        EditText password = findViewById(R.id.editText_login_password);
        Button login = findViewById(R.id.button_login_login);

        login.setOnClickListener(view -> {
            if (!username.getText().toString().isEmpty() || !password.getText().toString().isEmpty()) {
                if (username.getText().toString().trim().equals("user") && password.getText().toString().trim().equals("12345")) {
                    saveUserData();
                    login();
                } else {
                    Toast.makeText(this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "لطفا تمام گزینه ها را پر کنید", Toast.LENGTH_SHORT).show();
            }
        });
        //Keep returning false to Should Keep On Screen until ready to begin.
        splashScreen.setKeepOnScreenCondition(() -> keep);
        Handler handler = new Handler();
        handler.postDelayed(runner, DELAY);
    }

    /**Will cause a second process to run on the main thread**/
    private final Runnable runner = new Runnable() {
        @Override
        public void run() {
            keep = false;
        }
    };

    private void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "خوش آمدید", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveUserData() {
        SharedPreferences sharedPreferences =
                getApplicationContext().getSharedPreferences("USER_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("USER_STATE", true);
        editor.apply();
    }

    private boolean checkUserData() {
        SharedPreferences sharedPreferences =
                getApplicationContext().getSharedPreferences("USER_DATA", MODE_PRIVATE);
        boolean getUserState = sharedPreferences.getBoolean("USER_STATE", false);
        return getUserState;
    }

    @Override
    protected void onResume() {
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.S) //public static final S = 31 //Android 12
        {
            getSplashScreen().setSplashScreenTheme(R.style.Theme_App_Starting);
        }
        super.onResume();
    }
}