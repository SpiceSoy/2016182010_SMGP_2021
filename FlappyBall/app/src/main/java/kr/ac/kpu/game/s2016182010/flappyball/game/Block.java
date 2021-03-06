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
    private float x;
    private float y;
    RectF dstRect = new RectF();
    GameBitmap bitmap;

    public enum BLOCK_POSITION {
        TOP,
        BOTTOM,
    }

    public enum BLOCK_TYPE {
        NORMAL,
        RED,
        TYPE_COUNT
    }

    Block(BLOCK_POSITION position, BLOCK_TYPE type, float x, float y) {
        this.blockPosition = position;
        this.blockType = type;
        this.x = x;
        this.y = y;
        int resId = getResId();
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
    private int getResId() {
        switch (this.blockPosition){
            case BOTTOM:
                return this.blockType == BLOCK_TYPE.NORMAL ? R.mipmap.bg_pillardown : R.mipmap.bg_pillardown_red;
            case TOP:
                return this.blockType == BLOCK_TYPE.NORMAL ? R.mipmap.bg_pillarup : R.mipmap.bg_pillarup_red;
        }
        return -1;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public float getWidth() {
        return bitmap.getWidth();
    }
    public float getHeight() {
        return bitmap.getHeight();
    }

    public BLOCK_POSITION getBlockPosition() {
        return blockPosition;
    }

    public BLOCK_TYPE getBlockType() {
        return blockType;
    }
}
