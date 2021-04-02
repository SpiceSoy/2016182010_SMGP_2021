package kr.ac.kpu.game.s2016182010.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Ball {
    private static Bitmap bitmap;
    private float x;
    private float y;
    private float dx;
    private float dy;

    public Ball(float x, float y, float dx, float dy) {
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(
                    GameView.instance.getResources(),
                    R.mipmap.soccer_ball_240
            );
        }

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        this.x += this.dx * GameView.frameTime;
        this.y += this.dy * GameView.frameTime;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);
    }
}
