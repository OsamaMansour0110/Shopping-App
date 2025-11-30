package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlineshopping.UserFunctions.Login;

public class Splash_Online_Shopping extends AppCompatActivity {

    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_online_shopping);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.btnsound);

        start = (Button)findViewById(R.id.welcome_spl);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                Intent intent = new Intent(Splash_Online_Shopping.this, Login.class);
                startActivity(intent);
            }
        });
    }
}