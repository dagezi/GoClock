package com.example.goclock;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer for each player.
 */
public class GoTimer {
    private TextView timerView;
    private Button button;
    private boolean running;
    private int seconds = 60 * 60; // 1 Hour is the default
    private Timer timer;
    private OnClickListener onClickListner;

    public GoTimer(TextView timer, Button button) {
        this.timerView = timer;
        this.button = button;
    }

    public void init() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListner != null) {
                    onClickListner.onClick(GoTimer.this);
                }
            }
        });
        updateTimerView();
    }

    public interface OnClickListener {
        void onClick(GoTimer goTimer);
    }

    public void setOnClickListener(OnClickListener listener) {
        onClickListner = listener;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        updateTimerView();
    }

    public void pause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        running = false;
    }

    public void yield() {
        pause();
        button.setEnabled(false);
    }

    public void resume() {
        if (running) {
            return;
        }
        running = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seconds--;
                Activity activity = (Activity) timerView.getContext();
                assert activity != null;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateTimerView();
                    }
                });
            }
        }, 1000, 1000);
        button.setEnabled(true);
    }

    private void updateTimerView() {
        timerView.setText(String.format("%d:%02d:%02d",
                seconds / 3600, (seconds % 3600) / 60, (seconds % 60)));
    }

    public boolean isRunning() {
        return running;
    }
}
