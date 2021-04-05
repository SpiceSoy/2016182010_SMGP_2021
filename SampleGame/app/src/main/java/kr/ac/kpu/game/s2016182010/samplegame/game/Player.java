package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Player implements GameObject {
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

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        float left = this.x - Player.imageWidth / 2;
        float top = this.y - Player.imageHeight / 2;
        canvas.drawBitmap(bitmap, left, top, null);
    }
}
