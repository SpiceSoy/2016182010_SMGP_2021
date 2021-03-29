package kr.ac.kpu.game.s2016182010.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap bitmap;
    private float x;
    private float y;
    private long lastFrame;
    private float frameTime;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void initResources() {
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        x = 100;
        y = 100;
    }

    private void startUpdating() {
        doGameFrameLoop();
    }

    private void doGameFrameLoop() {
//        update();
        x += 100f * frameTime;
        y += 200f * frameTime;

//        draw();
        invalidate();
        Choreographer.getInstance().postFrameCallback(
                new Choreographer.FrameCallback() {
                    @Override
                    public void doFrame(long time) {
                        if(lastFrame == 0){
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
        Log.d(TAG, "Drawing at : " + x + ", " + y + " / frame time : " + frameTime);
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
