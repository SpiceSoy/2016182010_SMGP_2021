package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable {
    private static final float FRAMES_PER_SECOND = 8;
    private final float x;
    private final AnimationGameBitmap bitmap;
    private float y;
    private final int speed;

    public Enemy(float x, float y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

        bitmap = new AnimationGameBitmap(R.mipmap.enemy_01, FRAMES_PER_SECOND, 0);
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
    public RectF getBoundingRect() {
        return this.bitmap.getBoundingRect(x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, this.x, this.y);
    }
}
