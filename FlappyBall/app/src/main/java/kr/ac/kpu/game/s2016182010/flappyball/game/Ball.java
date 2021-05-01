package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class Ball implements GameObject {
    private static final float GRAVITY_FORCE = 9.8f * 100 * 7;
    private static final float SHOOT_FORCE = 5.5f;
    private static final float IMAGE_RATIO = 3.0f;
    private float positionX;
    private float positionY;
    private float velocityX;
    private float velocityY;


    GameBitmap bitmap;
    Ball(int resId, float x, float y) {
        bitmap = new GameBitmap(resId);
        this.positionX = x;
        this.positionY = y;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.velocityY += GRAVITY_FORCE * game.frameTime;
        this.positionX += this.velocityX * game.frameTime;
        this.positionY += this.velocityY * game.frameTime;
        if(this.positionY > GameView.instance.getHeight()){
            this.velocityY = 0;
        }
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
        bitmap.draw(canvas, positionX, positionY);
    }
}
