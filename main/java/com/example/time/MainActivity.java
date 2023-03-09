package com.example.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> cats = new ArrayList<>();
    private int catsek = 11;
    private TextView catTextViewPoints;
    private int catPoints = 0;
    private TextView catTimeSetText;
    private ConstraintLayout constraintLayout ;

    private TextView g1;
    private TextView g2;
    private Button button;
    ClockTime clock1;
    ClockTime clock2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catTextViewPoints = findViewById(R.id.textView5);
        catTimeSetText = findViewById(R.id.textView6);
        cats.add(findViewById(R.id.imageView2));
        cats.add(findViewById(R.id.imageView3));
        cats.add(findViewById(R.id.imageView4));
        cats.add(findViewById(R.id.imageView5));

        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        constraintLayout = findViewById(R.id.your_constraint_layout_id);
        clock1 = new ClockTime(true,g1,constraintLayout);
        clock1.StartT();
        clock2 = new ClockTime(true,g2,constraintLayout);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(clock1.Running()){
                clock1.StopT();
                clock2.StartT();
                button.setText("G2");
                if (clock1.shouldDisableButton()) {
                    button.setEnabled(false);
                }
            }else {
                clock2.StopT();
                clock1.StartT();
                button.setText("G1");
                if (clock2.shouldDisableButton()) {
                    button.setEnabled(false);
                }
            }
            }
        });
        ViewRoundImage();
        countCat();
    }

    private void countCat() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(catsek>=1) {
                    catsek--;
                    ViewRoundImage();
                    catTimeSetText.setText("time: " + String.valueOf(catsek));
                    handler.postDelayed(this, 1000);
                }
            }
        });

    }

    private void ViewRoundImage() {


        for(ImageView catsArt:cats){
            catsArt.setVisibility(View.INVISIBLE);
        }
            int idx = (int)(Math.random()*cats.size());

        if(catsek>=1) {
        cats.get(idx).setVisibility(View.VISIBLE);
        cats.get(idx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catPoints++;
                catTextViewPoints.setText("points: " + catPoints);
                ViewRoundImage();
            }
        });
    }}


}