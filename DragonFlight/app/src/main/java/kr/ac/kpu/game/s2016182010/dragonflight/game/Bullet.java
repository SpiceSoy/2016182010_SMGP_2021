package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;

public class Bullet implements GameObject {
    private static final float IMAGE_RATIO = 2;
    private final float x;
    private final Bitmap bitmap;
    private float y;
    private final int speed;
    private final float hw;
    private final float hh;

    public Bullet(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

        bitmap = GameBitmap.load(R.mipmap.laser_1);

        hw = bitmap.getWidth() / 2;
        hh = bitmap.getHeight() / 2;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += -speed * game.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float l = this.x - hw;
        float t = this.y - hh;
        canvas.drawBitmap(bitmap, l, t, null);

//        float left = this.x - hw * IMAGE_RATIO;
//        float top = this.y - hh * IMAGE_RATIO;
//        float right = this.x + hw * IMAGE_RATIO;
//        float bottom = this.y + hh * IMAGE_RATIO;
//        RectF dstRect = new RectF(left, top, right, bottom);
//        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
