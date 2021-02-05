package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import java.util.Locale;
import android.os.Handler;
import android.widget.TextView;


public class MainActivity extends Activity {
   private boolean running;
   private int seconds=0;
   private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    protected void onStop(){
        super.onStop();
        wasRunning = running;
        running=false;
    }
    //setTitle will only work before the activity is running.so,it should be in on create,start or resume
    @Override
    protected void onStart(){
        super.onStart();
        if(wasRunning){
            running=true;
        }
    }

    public void OnClickStart(View view){

        running=true;
    }

    public void OnClickStop(View view){

        running=false;
    }

    public void OnClickReset(View view){
        running=false;
        seconds = 0;
    }

    private void runTimer()
    {
        final TextView timeView =(TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
        @Override
            public void run()
            {int hours = seconds/3600;
            int minutes = (seconds%3600)/60;
            int secs = seconds%60;
            String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
            timeView.setText(time);
            if(running){
                seconds++;
            }
            handler.postDelayed(this,1000);
            }
           /* here you are adding the post delay in run because you can't or should add it in the runnable
           object.alternatively,you can also post outside but you need the reference variable which we didnt
           create here.it gets include in runnable by having it in the run.
            */
        });
    }

/* block comment
block comment */
}