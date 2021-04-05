package kr.ac.kpu.game.s2016182010.samplegame.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.s2016182010.samplegame.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView instance;
    private long lastFrame;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.instance = this;
        MainGame game = MainGame.get();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrameLoop();
    }

    private void doGameFrameLoop() {
//      update();
        MainGame game = MainGame.get();
        game.update();

//      draw();
        invalidate();
        Choreographer.getInstance().postFrameCallback(
                new Choreographer.FrameCallback() {
                    @Override
                    public void doFrame(long time) {
                        if (lastFrame == 0) {
                            lastFrame = time;
                        }
                        game.frameTime = ((float) (time - lastFrame) / 1000000000.0f);
                        doGameFrameLoop();
                        lastFrame = time;
                    }
                }
        );
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSize : " + w + ", " + h);
        MainGame game = MainGame.get();
        game.initResources();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame game = MainGame.get();
        game.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainGame game = MainGame.get();
        return game.onTouchEvent(event);
    }
}
