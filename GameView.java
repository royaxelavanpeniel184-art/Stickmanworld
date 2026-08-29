package com.stickmanworld;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float playerX = 280, playerY = 480, velocityY = 0;
    private boolean left, right;

    public GameView(Context context) { super(context); setFocusable(true); }

    @Override protected void onDraw(Canvas c) {
        int w = getWidth(), h = getHeight();
        c.drawColor(Color.rgb(225, 240, 255));
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.rgb(115, 185, 90)); c.drawRect(0, h - 105, w, h, p);
        p.setColor(Color.DKGRAY); p.setTextSize(28); c.drawText("STICKMAN WORLD", 30, 42, p);
        p.setTextSize(20); c.drawText("Move ◀ ▶   Jump ▲   Shadow is optional", 30, 70, p);

        if (left) playerX -= 7; if (right) playerX += 7;
        velocityY += .8f; playerY += velocityY;
        if (playerY > h - 180) { playerY = h - 180; velocityY = 0; }
        playerX = Math.max(45, Math.min(w - 45, playerX));

        drawStickman(c, playerX, playerY, Color.BLACK, 1.0f);
        // Optional autonomous Shadow companion.
        float sx = playerX + 130 + (float)Math.sin(System.currentTimeMillis()/450.0) * 28;
        drawStickman(c, sx, playerY + 10, Color.rgb(55,55,65), .75f);

        p.setColor(Color.argb(155, 35, 35, 35));
        c.drawCircle(80, h-60, 42, p); c.drawCircle(180, h-60, 42, p); c.drawCircle(w-90, h-60, 42, p);
        p.setColor(Color.WHITE); p.setTextSize(34);
        c.drawText("◀", 61, h-49, p); c.drawText("▶", 161, h-49, p); c.drawText("▲", w-106, h-49, p);
        postInvalidateDelayed(16);
    }

    private void drawStickman(Canvas c, float x, float y, int color, float s) {
        p.setColor(color); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8*s);
        c.drawCircle(x, y-70*s, 24*s, p); c.drawLine(x, y-45*s, x, y+25*s, p);
        c.drawLine(x, y-20*s, x-35*s, y+5*s, p); c.drawLine(x, y-20*s, x+35*s, y+5*s, p);
        c.drawLine(x, y+25*s, x-30*s, y+75*s, p); c.drawLine(x, y+25*s, x+30*s, y+75*s, p);
        p.setStyle(Paint.Style.FILL);
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        float x=e.getX(), y=e.getY();
        if (e.getAction()==MotionEvent.ACTION_DOWN || e.getAction()==MotionEvent.ACTION_MOVE) {
            left = x < 130 && y > getHeight()-140; right = x >= 130 && x < 240 && y > getHeight()-140;
            if (x > getWidth()-170 && y > getHeight()-140 && velocityY == 0) velocityY=-17;
            return true;
        }
        if (e.getAction()==MotionEvent.ACTION_UP) { left=false; right=false; return true; }
        return true;
    }
}
