package com.example.spinthebottle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bottle;
    //declaring a random
    private Random random = new Random();
    private int lastDir = 100;
    private boolean spinning;
    public String string;

    ArrayList<String> myArrayOfStrings =
            new ArrayList<>(Arrays.asList(
                    "Your Task is to Make Someone Fool.",
                    "Your Task is to do Mimicry of animals.",
                    "Your Task is to Talk with a Random Person nearest to you.",
                    "Your Task is to Make a new Friend.",
                    "Your Task is to Slap any of your Friend.",
                    "Your Task is to do Acting Like a Beggar.",
                    "Your Task is to Sing a Song (Any Song)",
                    "Your Task is to do Acting of Shahrukh Khan",
                    "Your Task is to Give 10Rs to any of your Friend",
                    "Your Task is to Take a Selfie with any Random Person nearest to you."
            ));

    //Button button = findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle = findViewById(R.id.bottle);
    }

    //custom TASK
    public void customTask(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Your Custom Task");

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //appending task
                string = input.getText().toString();
                myArrayOfStrings.add(string);

                Toast.makeText(MainActivity.this, "Task Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void spinBottle(final View view){
        //if spinning is not true
        if (!spinning) {
            //defining direction
            int newDir = random.nextInt(3600); //declaring no. b/w 0-3600
            float pivotX = bottle.getWidth() / 2; //setting on middle
            float pivotY = bottle.getHeight() / 2; //setting on middle

            //creating animation
            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);  //setting duration of rotation
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
                    showTask((View) view);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            bottle.startAnimation(rotate); //starting animation
        }

    }
    //showing TASK in dialogBox
    // Button button = findViewById(R.id.button);
    public void showTask( View view){

        //declaring random TASKS

        Random r = new Random();
        String randomStr = myArrayOfStrings.get(r.nextInt(myArrayOfStrings.size()));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(randomStr);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish();

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        //sending string to another method

    }
}