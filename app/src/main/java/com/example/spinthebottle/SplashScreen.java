package com.example.spinthebottle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    public String string;
    public ArrayList<String> myArrayOfStrings;
    private ImageView bottle;
    //declaring a random
    //private Random random = new Random();
    private int lastDir = 100;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        bottle = findViewById(R.id.bottle);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                //startActivity(intent);
                //finish();

                //if spinning is not true
                if (!spinning) {
                    //defining direction
                    int newDir = 3600; //declaring no. b/w 0-3600
                    float pivotX = bottle.getWidth() / 2; //setting on middle
                    float pivotY = bottle.getHeight() / 2; //setting on middle

                    //creating animation
                    Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
                    rotate.setDuration(2000);  //setting duration of rotation
                    rotate.setFillAfter(true); //it means, we want to imageView in initial position
                    //setting animation listener
                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spinning = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            spinning = false;
                            startActivity(intent);
                            finish();
                            // showTask((View) view);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    lastDir = newDir;
                    bottle.startAnimation(rotate); //starting animation


                }

            }
        },800);
    }
}
