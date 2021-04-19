package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;

public class Bullet implements GameObject {
    private final float x;
    private final GameBitmap bitmap;
    private float y;
    private final int speed;

    public Bullet(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

        bitmap = new GameBitmap(R.mipmap.laser_1);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += -speed * game.frameTime;

        if(y < 0 - this.bitmap.getHeight()) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }
}
