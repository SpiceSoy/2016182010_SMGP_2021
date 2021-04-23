package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.Recyclable;

public class Bullet implements GameObject, BoxCollidable, Recyclable {
    private static final String TAG = Bullet.class.getSimpleName();
    private float x;
    private final GameBitmap bitmap;
    private float y;
    private int speed;

    private Bullet(float x, float y, int speed) {
        this.init(x, y, speed);
        Log.d(TAG, "Create New Bullet");
        bitmap = new GameBitmap(R.mipmap.laser_1);
    }

    private void init(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

//    private static ArrayList<Bullet> recyclePool = new ArrayList<>();

    public static Bullet get(float x, float y, int speed) {
        MainGame game = MainGame.get();
        Bullet bullet = (Bullet) game.get(Bullet.class);
        if(bullet == null) {
            return new Bullet(x, y, speed);
        }
        bullet.init(x, y, speed);
        return bullet;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += -speed * game.frameTime;

        if (y < 0 - this.bitmap.getHeight()) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        this.bitmap.getBoundingRect(x, y, rect);
    }

    @Override
    public void recycle() {

    }

//    public void recycle() {
//        recyclePool.add(this);
//    }
}
