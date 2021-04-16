package kr.ac.kpu.game.s2016182010.samplegame.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.kpu.game.s2016182010.samplegame.framework.Sound;
import kr.ac.kpu.game.s2016182010.samplegame.game.MainGame;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView instance;
    private long lastFrame;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.instance = this;
        Sound.init(context);
    }

    private void update() {
        MainGame game = MainGame.get();
        game.update();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSize : " + w + ", " + h);

        MainGame game = MainGame.get();
        boolean justInitialized = game.initResources();
        if (justInitialized) {
            requestCallback();
        }
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

    private void requestCallback() {
        Choreographer.getInstance().postFrameCallback(
                new Choreographer.FrameCallback() {
                    @Override
                    public void doFrame(long time) {
                        if (lastFrame == 0) {
                            lastFrame = time;
                        }
                        MainGame game = MainGame.get();
                        game.frameTime = ((float) (time - lastFrame) / 1000000000.0f);
                        update();
                        lastFrame = time;
                        requestCallback();
                    }
                }
        );
    }
}
