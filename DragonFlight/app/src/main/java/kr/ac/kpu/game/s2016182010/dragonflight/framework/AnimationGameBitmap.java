package kr.ac.kpu.game.s2016182010.dragonflight.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final int frameHeight;
    private final long createdOn;
    private final float framePerSec;
    private final int frameCount;


    public AnimationGameBitmap(int resId, float frameRate, int frameCount) {
        super(resId);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();

        this.framePerSec = frameRate;
        if (frameCount == 0) {
            this.frameCount = imageWidth / imageHeight;
        } else {
            this.frameCount = frameCount;
        }
        frameWidth = imageWidth / this.frameCount;
        frameHeight = imageHeight;
        createdOn = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, float x, float y) {
        int hw = (int) (frameWidth * 0.5 * GameView.MULTIPLIER);
        int hh = (int) (frameHeight * 0.5 * GameView.MULTIPLIER);

        int elapsed = (int) (System.currentTimeMillis() - createdOn);
        int frameIndex = Math.round(elapsed * 0.001f * framePerSec) % frameCount;

        Rect src = new Rect(
                (frameWidth * frameIndex), 0, (frameWidth * (frameIndex + 1)), frameHeight
        );
        RectF dst = new RectF(
                x - hw, y - hh, x + hw, y + hh
        );


        canvas.drawBitmap(bitmap, src, dst, null);
    }

    @Override
    public int getWidth() {
        return this.frameWidth;
    }

    @Override
    public int getHeight() {
        return this.frameHeight;
    }
}
