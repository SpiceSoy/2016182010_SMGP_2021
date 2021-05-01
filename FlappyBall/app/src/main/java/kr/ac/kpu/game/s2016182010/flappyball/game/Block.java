package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.R;
import kr.ac.kpu.game.s2016182010.flappyball.framework.BoxCollide;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;

public class Block implements GameObject, BoxCollide {

    private final BLOCK_POSITION blockPosition;
    private final BLOCK_TYPE blockType;
    private final float x;
    private final float y;
    RectF dstRect = new RectF();
    GameBitmap bitmap;

    enum BLOCK_POSITION {
        TOP,
        BOTTOM,
    }
    enum BLOCK_TYPE {
        NORMAL
    }

    Block(BLOCK_POSITION position, BLOCK_TYPE type, float x, float y) {
        this.blockPosition = position;
        this.blockType = type;
        this.x = x;
        this.y = y;
        int resId = this.blockPosition == BLOCK_POSITION.TOP ? R.mipmap.bg_pillarup : R.mipmap.bg_pillardown;
        this.bitmap = new GameBitmap(resId);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        this.bitmap.draw(canvas, x, getRectCenterY());
    }

    @Override
    public void getBoundingRect(RectF rect) {
        this.bitmap.getBoundingRect(x, getRectCenterY(), rect);
    }

    private float getRectCenterY() {
        float hh = (this.blockPosition == BLOCK_POSITION.TOP ? 0.5f : -0.5f) * this.bitmap.getHeight();
        return this.y + hh;
    }
}
