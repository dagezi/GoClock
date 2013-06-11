package com.example.goclock;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TextView which draws texts upside down.
 */
public class UpsideDownTextView extends TextView {
    public UpsideDownTextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas){
        canvas.translate(getWidth(), getHeight());
        canvas.rotate(180);

        canvas.clipRect(0, 0, getWidth(), getHeight(), android.graphics.Region.Op.REPLACE);
        super.draw(canvas);
    }
}
