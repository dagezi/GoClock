package com.example.goclock;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private GoTimer blackTimer;
    private GoTimer whiteTimer;

    private GoTimer currentTimer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.white_button);
        TextView timerView = (TextView) findViewById(R.id.white_timer);
        whiteTimer = new GoTimer(timerView, button);

        button = (Button) findViewById(R.id.black_button);
        timerView = (TextView) findViewById(R.id.black_timer);
        blackTimer = new GoTimer(timerView, button);

        whiteTimer.setEnemyTimer(blackTimer);
        blackTimer.setEnemyTimer(whiteTimer);

        whiteTimer.init();
        blackTimer.init();
        currentTimer = blackTimer;

        final Button toggleButton = (Button) findViewById(R.id.toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {
                    if (blackTimer.isRunning()) {
                        currentTimer = blackTimer;
                    } else {
                        currentTimer = whiteTimer;
                    }
                    currentTimer.pause();
                    running = false;
                    toggleButton.setTag(R.string.start);
                } else {
                    currentTimer.resume();
                    running = true;
                    toggleButton.setText(R.string.pause);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
