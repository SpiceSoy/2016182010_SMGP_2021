package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable {
    private static final float FRAMES_PER_SECOND = 8;
    private final float x;
    private final AnimationGameBitmap bitmap;
    private float y;
    private final int level;
    private final int speed;

    private static final int RESOURCE_IDS[] = {
            R.mipmap.enemy_01,R.mipmap.enemy_02,R.mipmap.enemy_03,R.mipmap.enemy_04,R.mipmap.enemy_05,
            R.mipmap.enemy_06,R.mipmap.enemy_07,R.mipmap.enemy_08,R.mipmap.enemy_09,R.mipmap.enemy_10,
            R.mipmap.enemy_11,R.mipmap.enemy_12,R.mipmap.enemy_13,R.mipmap.enemy_14,R.mipmap.enemy_15,
            R.mipmap.enemy_16,R.mipmap.enemy_17,R.mipmap.enemy_18,R.mipmap.enemy_19,R.mipmap.enemy_20
    };

    public Enemy(int level, float x, float y, int speed) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.speed = speed;
        int resId = RESOURCE_IDS[level - 1];
        bitmap = new AnimationGameBitmap(resId, FRAMES_PER_SECOND, 0);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += speed * game.frameTime;

        if(y > GameView.instance.getHeight()) {
            game.remove(this);
        }
    }

    @Override
    public void getBoundingRect(RectF rect) {
        this.bitmap.getBoundingRect(x, y, rect);
    }
    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }
}
