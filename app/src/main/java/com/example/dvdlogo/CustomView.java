package com.example.dvdlogo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CustomView extends View {
    private Paint paint;
    private int x = 0, y = 100;
    private int dx = 5, dy = 5;
    private boolean isPartyMode = false;
    private Random random = new Random();
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate(); // Refresh the view
        }
    };

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);

        handler.postDelayed(runnable, 30); // Refresh every 30ms
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isPartyMode) {
            canvas.drawColor(randomColor()); // Change background color in Party Mode
        } else {
            canvas.drawColor(Color.BLACK); // Default background color
        }

        canvas.drawText("Bouncing Text", x, y, paint);

        // Bounce logic
        x += dx;
        y += dy;
        if (x < 0 || x + paint.measureText("Bouncing Text") > getWidth()) {
            dx = -dx;
        }
        if (y < 50 || y > getHeight()) {
            dy = -dy;
        }

        handler.postDelayed(runnable, 30); // Schedule next frame
    }

    public void setPartyMode(boolean isPartyMode) {
        this.isPartyMode = isPartyMode;
        if (isPartyMode) {
            dx = 10; // Increase speed in Party Mode
            dy = 10;
        } else {
            dx = 5;
            dy = 5;
        }
    }

    private int randomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
