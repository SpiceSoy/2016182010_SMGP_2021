package kr.ac.kpu.game.s2016182010.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Ball {
    private static Bitmap bitmap;
    private static int w;
    private static int h;

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
            Ball.w = bitmap.getWidth();
            Ball.h = bitmap.getHeight();
        }

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        this.x += this.dx * GameView.frameTime;
        this.y += this.dy * GameView.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        if (x < 0 || x + Ball.w > w){
            this.dx *= -1;
        }

        if (y < 0 || y + Ball.h > h){
            this.dy *= -1;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);
    }
}