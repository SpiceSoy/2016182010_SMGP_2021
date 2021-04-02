package kr.ac.kpu.game.s2016182010.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
    private static Bitmap bitmap;
    private static int imageWidth;
    private static int imageHeight;

    private float x;
    private float y;

    private float dx;
    private float dy;

    public Player(float x, float y, float dx, float dy) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(
                    GameView.instance.getResources(),
                    R.mipmap.plane_240
            );
            Player.imageWidth = bitmap.getWidth();
            Player.imageHeight = bitmap.getHeight();
        }

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        float left = this.x - Player.imageWidth / 2;
        float top = this.y - Player.imageHeight / 2;
        canvas.drawBitmap(bitmap, left, top, null);
    }
}
