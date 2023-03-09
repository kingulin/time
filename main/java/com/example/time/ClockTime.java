package com.example.time;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ClockTime {

    private int sek = 10;
    private int sekCount = sek ;
    private boolean isRunning;
    private TextView timeTextView;
    private CountDownTimer timer;
    private ConstraintLayout layout;


    public ClockTime(boolean isRunning, TextView timeTextView, ConstraintLayout layout) {
        this.isRunning = isRunning;
        this.timeTextView = timeTextView;
        this.layout = layout;
    }


    public boolean Running() {
        return isRunning;
    }
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void StartT(){
        timer = new CountDownTimer(sek*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sek = (int) millisUntilFinished/1000;
                ViewTimeLeft(sek);
                sekCount--;
                if(sekCount<=15){
                   layout.setBackgroundResource(R.drawable.drawable_background);
                }else{

                    layout.setBackgroundResource(R.drawable.white);
                }

            }

            @Override
            public void onFinish() {
                timeTextView.setText("Koniec");

            }
        };
        timer.start();
        isRunning = true;
    }


    public boolean shouldDisableButton() {
        return (sekCount <= 0);
    }
    private void ViewTimeLeft(int s){
        int min = s/60;
        int seku = s%60;
        timeTextView.setText(String.format("%02d : %02d", min, seku));
    }
    public void StopT(){
        timer.cancel();
        isRunning = false;
    }
}
