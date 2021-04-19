package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;

public class Player implements GameObject {
    private static final float IMAGE_RATIO = 2;
    private Bitmap bitmap;
    private int imageWidth;
    private int imageHeight;
    private float speed;

    private float x;
    private float y;

    private float tx;

    public Player(float x, float y) {
        bitmap = GameBitmap.load(R.mipmap.fighter);
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
        this.speed = 1000;
        this.x = x;
        this.y = y;
        this.tx = x;
    }

    public void moveTo(float x, float y) {
        this.tx = x;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        float dx = (tx < x ? -1 : 1) * speed * game.frameTime;
        x += dx;
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        float hw = imageWidth / 2;
        float hh = imageHeight / 2;

        float left = this.x - hw * IMAGE_RATIO;
        float top = this.y - hh * IMAGE_RATIO;
        float right = this.x + hw * IMAGE_RATIO;
        float bottom = this.y + hh * IMAGE_RATIO;
//        Rect srcRect = new Rect(0, 0, imageWidth, imageHeight);
        RectF dstRect = new RectF(left, top, right, bottom);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
