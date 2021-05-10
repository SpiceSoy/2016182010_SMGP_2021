package kr.ac.kpu.game.s2016182010.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    private final int imageWidth;
    private final int imageHeight;
    protected int frameWidth;
    protected int frameHeight;
    protected final long createdOn;
    protected final float framePerSec;
    protected int frameCount;
    protected Rect srcRect = new Rect();


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

        srcRect.set((frameWidth * frameIndex), 0, (frameWidth * (frameIndex + 1)), frameHeight);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
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
