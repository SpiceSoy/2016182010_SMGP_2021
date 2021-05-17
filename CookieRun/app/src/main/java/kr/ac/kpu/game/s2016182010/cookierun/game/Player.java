package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.cookierun.framework.game.BaseGame;

public class Player implements GameObject, BoxCollidable {
    private static final float GRAVITY = 2500;
    private static final float JUMP_FORCE = 1200.0f;
    private static final String TAG = Player.class.getSimpleName();
    private float fireTime;
    private IndexedAnimationGameBitmap charBitmap;
    private float speed;

    private float x;
    private float y;

    private float tx;
    private float ty;

    private final float groundY;

    private float verticalSpeed;

    private static final int[] ANIM_INDICES_RUNNING = {100, 101, 102};
    private static final int[] ANIM_INDICES_JUMP = {7, 8};
    private static final int[] ANIM_INDICES_DOUBLE_JUMP = {1,2,3,4};

    private enum State {
        running,
        jump,
        doubleJump,
        slide,
        hit,
    }

    private State state = State.running;

    public Player(float x, float y) {
        this.speed = 1000;
        this.x = x;
        this.y = y;
        this.groundY = y;
        this.tx = x;
        this.fireTime = 0;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 4.5f, 0);
        this.charBitmap.setIndices(100, 101, 102, 103);
    }

    public void moveTo(float x, float y) {
        this.tx = x;
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();

        if (this.state == State.jump || this.state == State.doubleJump ) {
            this.y += verticalSpeed * game.frameTime;

            verticalSpeed += GRAVITY * game.frameTime;

            if (this.y >= groundY) {
                this.y = groundY;
                verticalSpeed = 0;
                setState(State.running);
            }
        }
    }

    private void setState(State state) {
        this.state = state;
        switch (state) {
            case running: charBitmap.setIndices(ANIM_INDICES_RUNNING); break;
            case jump: charBitmap.setIndices(ANIM_INDICES_JUMP); break;
            case doubleJump: charBitmap.setIndices(ANIM_INDICES_DOUBLE_JUMP); break;
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

    public void jump() {
//        if(state != State.running && state != State.jump && state != State.hit) {
        if (state == State.running) {
            setState(State.jump);
            this.verticalSpeed = -JUMP_FORCE;
        } else if(state == State.jump) {
            setState(State.doubleJump);
            verticalSpeed = -JUMP_FORCE;
        } else {
            Log.d(TAG, "Current state is can't Jump");
        }
    }
}