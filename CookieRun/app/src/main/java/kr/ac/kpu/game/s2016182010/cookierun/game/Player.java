package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.GameObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.cookierun.framework.MainGame;

public class Player implements GameObject, BoxCollidable {
    private static final int BULLET_SPEED = 1500;
    private float fireTime;
    private IndexedAnimationGameBitmap charBitmap;
    private float speed;

    private float x;
    private float y;

    private float tx;
    private float ty;

    public Player(float x, float y) {
        this.speed = 1000;
        this.x = x;
        this.y = y;
        this.tx = x;
        this.fireTime = 0;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 4.5f,  0);
        this.charBitmap.setIndices(100, 101, 102, 103);
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
    public void getBoundingRect(RectF rect) {
         this.charBitmap.getBoundingRect(x, y, rect);
    }

    @Override
    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, this.x, this.y);
    }
}
