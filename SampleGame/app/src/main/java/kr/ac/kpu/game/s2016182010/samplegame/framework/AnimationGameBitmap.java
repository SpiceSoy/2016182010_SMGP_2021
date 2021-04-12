package kr.ac.kpu.game.s2016182010.samplegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    public static final int IMAGE_RATIO = 4;
    private final Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final int frameHeight;
    private final long createdOn;
    private final float framePerSec;
    private final int frameCount;

    public AnimationGameBitmap(int resId, float frameRate, int frameCount) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources res = GameView.instance.getResources();
        bitmap = BitmapFactory.decodeResource(res, resId, options);

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
        this.draw(canvas, x, y, 0);
    }

    public void draw(Canvas canvas, float x, float y, float degree) {
        int hw = (int) (frameWidth *  0.5 * IMAGE_RATIO);
        int hh = (int) (frameHeight * 0.5 * IMAGE_RATIO);

        int elapsed = (int) (System.currentTimeMillis() - createdOn);
        int frameIndex = Math.round(elapsed * 0.001f * framePerSec) % frameCount;

        Rect src = new Rect(
                (frameWidth * frameIndex), 0, (frameWidth * (frameIndex + 1)), frameHeight
        );
        RectF dst = new RectF(
                x - hw, y - hh, x + hw, y + hh
        );

        if (degree != 0) {
            canvas.save();
            canvas.rotate(degree, x, y);
        }
        canvas.drawBitmap(bitmap, src, dst, null);
        if (degree != 0) {
            canvas.restore();
        }
    }

    public int getWidth(){
        return this.frameWidth * IMAGE_RATIO;
    }

    public int getHeight(){
        return this.frameHeight * IMAGE_RATIO;
    }
}
