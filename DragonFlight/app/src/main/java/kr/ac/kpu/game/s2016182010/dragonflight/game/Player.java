package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;

public class Player implements GameObject {
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_RPS = 7.5f;
    private static final float FIRE_INTERVAL = 1.0f / FIRE_RPS;
    private float fireTime;
    private GameBitmap bitmap;
    private float speed;

    private float x;
    private float y;

    private float tx;

    public Player(float x, float y) {
        bitmap = new GameBitmap(R.mipmap.fighter);
        this.speed = 1000;
        this.x = x;
        this.y = y;
        this.tx = x;
        this.fireTime = 0;
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
        fireTime += game.frameTime;
        if (fireTime >= FIRE_INTERVAL) {
            fireBullet();
            fireTime -= FIRE_INTERVAL;
        }
    }

    private void fireBullet() {
        Bullet bullet = new Bullet(this.x, this.y, BULLET_SPEED);
        MainGame game = MainGame.get();
        game.add(bullet);
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }
}
