package kr.ac.kpu.game.s2016182010.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static final int BALL_COUNT = 100;
    public static GameView instance;

    private ArrayList<Ball> balls = new ArrayList<>();

    private long lastFrame;
    public static float frameTime;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.instance = this;
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrameLoop();
    }

    private void initResources() {
        Random rand = new Random();
        for (int i = 0; i < BALL_COUNT; ++i) {
            float x = rand.nextInt(1000);
            float y = rand.nextInt(1000);
            float dx = rand.nextFloat() * 1000 - 500;
            float dy = rand.nextFloat() * 1000 - 500;
            balls.add(new Ball(x, y, dx, dy));
        }
    }

    private void doGameFrameLoop() {
//      update();
        for (Ball b : balls) {
            b.update();
        }

//      draw();
        invalidate();
        Choreographer.getInstance().postFrameCallback(
                new Choreographer.FrameCallback() {
                    @Override
                    public void doFrame(long time) {
                        if (lastFrame == 0) {
                            lastFrame = time;
                        }
                        frameTime = ((float) (time - lastFrame) / 1000000000.0f);
                        doGameFrameLoop();
                        lastFrame = time;
                    }
                }
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Ball b : balls) {
            b.draw(canvas);
        }
    }
}
