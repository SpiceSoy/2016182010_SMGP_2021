package kr.ac.kpu.game.s2016182010.cookierun.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class IndexedGameBitmap extends GameBitmap {
    private final String TAG = IndexedGameBitmap.class.getSimpleName();
    protected final int width;
    protected final int height;
    protected final int border;
    protected final int spacing;
    private final int xCount;
    protected Rect srcRect = new Rect();

    public IndexedGameBitmap(int resId, int width, int height, int xCount, int border, int spacing) {
        super(resId);
        this.width = width;
        this.height = height;
        this.xCount = xCount;
        this.border = border;
        this.spacing = spacing;
    }

    public void setIndex(int index) {
        int x = index % xCount;
        int y = index / xCount;
        int l = border + x * (width + spacing);
        int t = border + y * (height + spacing);
        int r = l + width;
        int b = t + height;
        this.srcRect.set(l, t, r, b);
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        float hw = (int) (width * 0.5 * GameView.MULTIPLIER);
        float hh = (int) (height * 0.5 * GameView.MULTIPLIER);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
    public void draw(Canvas canvas, RectF destRect){
        canvas.drawBitmap(bitmap, srcRect, destRect, null);
    }
}
