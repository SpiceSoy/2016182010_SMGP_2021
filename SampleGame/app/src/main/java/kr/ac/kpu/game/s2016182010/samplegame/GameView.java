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

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static GameView instance;

    private Ball b1;
    private Ball b2;

    private long lastFrame;
    public static float frameTime;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.instance = this;

        b1 = new Ball(100, 100, 100, 200);
        b2 = new Ball(1000, 100, -50, 150);

        startUpdating();
    }

    private void startUpdating() {
        doGameFrameLoop();
    }

    private void doGameFrameLoop() {
//      update();
        b1.update();
        b2.update();

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
        b1.draw(canvas);
        b2.draw(canvas);
    }
}
