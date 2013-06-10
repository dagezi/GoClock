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

        button = (Button) findViewById(R.id.toggle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackTimer.resume();
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
