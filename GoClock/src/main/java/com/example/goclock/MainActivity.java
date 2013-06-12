package com.example.goclock;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements GoTimer.OnClickListener {
    private GoTimer[] timers = new GoTimer[2];

    private int currentTimerIdx;

    /** Indicating the timer is already running. Note that it's false while the play is paused. */
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGoTimer(0, R.id.black_button, R.id.black_timer);
        initGoTimer(1, R.id.white_button, R.id.white_timer);

        currentTimerIdx = 0;

        final Button pauseButton = (Button) findViewById(R.id.pause);
        pauseButton.setEnabled(false);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
            }
        });
    }

    private void initGoTimer(int timerIndex, int buttonId, int timerId) {
        Button button = (Button) findViewById(buttonId);
        TextView timerView = (TextView) findViewById(timerId);
        timers[timerIndex] = new GoTimer(timerView, button);
        timers[timerIndex].init();
        timers[timerIndex].setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // GoTimer.OnClickListener interface
    @Override
    public void onClick(GoTimer goTimer) {
        if (running) {
            timers[currentTimerIdx].finishTurn();
            currentTimerIdx = (currentTimerIdx + 1) % 2;
            timers[currentTimerIdx].startTurn();
        } else {
            int i;
            for (i = 0; i < timers.length && timers[i] != goTimer; i++) {
                // Nothing
            }
            startTimer(i);
        }
    }

    public void startTimer(int timerIndex) {
        timers[0].resumeGame();
        timers[1].resumeGame();
        currentTimerIdx = timerIndex;
        timers[timerIndex].startTurn();
        running = true;
        Button pauseButton = (Button)findViewById(R.id.pause);
        pauseButton.setEnabled(true);
        Button settingButton = (Button) findViewById(R.id.setting);
        settingButton.setEnabled(false);
    }

    public void pauseTimer() {
        timers[currentTimerIdx].pauseGame();
        running = false;
        Button pauseButton = (Button)findViewById(R.id.pause);
        pauseButton.setEnabled(false);
        Button settingButton = (Button) findViewById(R.id.setting);
        settingButton.setEnabled(true);
    }
}
