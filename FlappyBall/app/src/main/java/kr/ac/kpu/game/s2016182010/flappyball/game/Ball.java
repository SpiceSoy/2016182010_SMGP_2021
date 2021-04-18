package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class Ball implements GameObject {
    private static final float GRAVITY_FORCE = 9.8f * 100;
    private static final float SHOOT_FORCE = 10.0f;
    private static final float IMAGE_RATIO = 3.0f;
    private float positionX;
    private float positionY;
    private float velocityX;
    private float velocityY;


    Bitmap bitmap;
    Rect srcRect;
    RectF dstRect = new RectF();


    Ball(int resId, float x, float y) {
        bitmap = GameBitmap.load(resId);
        this.positionX = x;
        this.positionY = y;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.velocityY += GRAVITY_FORCE * game.frameTime;
        this.positionX += this.velocityX * game.frameTime;
        this.positionY += this.velocityY * game.frameTime;
        this.positionX = Math.max(Math.min(this.positionX, GameView.instance.getWidth()),0);
        this.positionY = Math.max(Math.min(this.positionY, GameView.instance.getHeight()),0);
    }

    public void onCollisionBlock() {

    }

    public void shoot(float startX, float startY, float endX, float endY) {
        float dx = endX - startX;
        float dy = endY - startY;
//        float length = (float) Math.sqrt(dx*dx + dy*dy);
//        float ndx = dx / length;
//        float ndy = dy / length;
        this.velocityX += dx * SHOOT_FORCE;
        this.velocityY += dy * SHOOT_FORCE;

    }

    @Override
    public void draw(Canvas canvas) {
        float hw = bitmap.getWidth() * 0.5f * IMAGE_RATIO;
        float hh = bitmap.getHeight() * 0.5f * IMAGE_RATIO;
        float l = this.positionX - hw;
        float r = this.positionX + hw;
        float t = this.positionY - hh;
        float b = this.positionY + hh;
        dstRect.set(l,t,r,b);
        canvas.drawBitmap(bitmap, null, dstRect, null );
    }
}
