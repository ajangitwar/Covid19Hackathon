package com.aj.toinshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FullscreenActivity extends AppCompatActivity {
    private ImageView iv;
    TextView tv;
    Animation a,a2;
    public Window window;
    OwnerSes ownerSes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        ownerSes = new OwnerSes(FullscreenActivity.this);
        iv = findViewById(R.id.imageView);
        a = AnimationUtils.loadAnimation(this,R.anim.myalpha);
        iv.startAnimation(a);

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4050);
                    if (!ownerSes.getOwner_name().equals("")) {
                        Intent homeIntent = new Intent(FullscreenActivity.this, Home.class);
                        startActivity(homeIntent);
                        finish();
                    } else {
                        startActivity(new Intent(FullscreenActivity.this,Authentication.class));
                        finish();
                    }
                }
                catch (Exception e){e.printStackTrace();}
            }
        };
        t.start();
    }
}
