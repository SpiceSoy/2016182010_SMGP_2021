package kr.ac.kpu.game.s2016182010.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class IndexedAnimationGameBitmap extends AnimationGameBitmap {
    private final String TAG = IndexedAnimationGameBitmap.class.getSimpleName();
    private ArrayList<Rect> srcRects;

    public IndexedAnimationGameBitmap(int resId, float frameRate, int frameCount) {
        super(resId, frameRate, frameCount);
        this.frameWidth = 270;
        this.frameHeight = 270;
    }

    public void setIndices(int... indices) {
        srcRects = new ArrayList<Rect>();
        for (int index : indices) {
            int x = index % 100;
            int y = index / 100;
            //TODO 이거 왜 INDEX 쓰시냐
            int l = 2 + x * 272;
            int t = 2 + y * 272;
            int r = l + 270;
            int b = t + 270;
            srcRects.add(new Rect(l, t, r, b));
        }
        this.frameCount = srcRects.size();
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int fw = frameWidth;
        int fh = frameHeight;

        float hw = (int) (fw * 0.5 * GameView.MULTIPLIER);
        float hh = (int) (fh * 0.5 * GameView.MULTIPLIER);

        int elapsed = (int) (System.currentTimeMillis() - createdOn);
        int frameIndex = Math.round(elapsed * 0.001f * framePerSec) % frameCount;

//        srcRect.set((fw * frameIndex), 0, (fw * (frameIndex + 1)), fh);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}
