package com.cobanogluhasan.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     SeekBar timerSeekBar;
     TextView timerTextView;
     Boolean counterIsActive=false;
     Button button;
     CountDownTimer countDownTimer;

     public void resetTimer() {
         timerTextView.setText("0:30");
         timerSeekBar.setEnabled(true);
         timerSeekBar.setProgress(30);
         countDownTimer.cancel();
         button.setText("Start!!");
         counterIsActive = false;
     }


     public void updateTimer(int secondsLeft) {

         int min = (int) secondsLeft/60; // if we cast it to an integer it'll round down.
         int seconds =  secondsLeft - min* 60;

         String zero="";

         if (seconds<10) {
             zero ="0";
         }

         timerTextView.setText(Integer.toString(min) + ":" + zero + String.valueOf(seconds));


     }

    public void controlTimer(View view) {

         if(counterIsActive==false) {

             counterIsActive = true;
             timerSeekBar.setEnabled(false);
             button.setText("Stop");


              countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) { // wanna count down every second
                 //we get total miliseconds from timerSeekBar.getProgress() (its seconds ) so multiply it with 1000 to find multiseconds

                 @Override
                 public void onTick(long millisUntilFinished) {

                     updateTimer((int) millisUntilFinished / 1000);
                 }

                 @Override
                 public void onFinish() {
                     resetTimer();

                     MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhoorn);
                     mediaPlayer.start();

                 }
             }.start();

             }

         else {
           resetTimer();

         }


             };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button = (Button) findViewById(R.id.button) ;

        timerSeekBar.setMax(720); //write minutes as a second. 12min=720 sec
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }
}
