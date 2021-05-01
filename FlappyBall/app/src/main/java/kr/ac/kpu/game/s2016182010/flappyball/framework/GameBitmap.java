package kr.ac.kpu.game.s2016182010.flappyball.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();

    public static Bitmap load(int resId) {
        Bitmap bitmap;
        if (bitmaps.containsKey(resId)) {
            bitmap = bitmaps.get(resId);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Resources res = GameView.instance.getResources();
            bitmap = BitmapFactory.decodeResource(res, resId, options);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }

    protected final Bitmap bitmap;
    protected RectF dstRect = new RectF();

    public GameBitmap(int resId) {
        bitmap = load(resId);
    }

    public float getWidth() {
        return bitmap.getWidth() * GameView.MULTIPLIER;
    }

    public float getHeight() {
        return bitmap.getHeight() * GameView.MULTIPLIER;
    }

    public void getBoundingRect(float x, float y, RectF rect){
        float hw = this.getWidth() / 2;
        float hh = this.getHeight() / 2;

        float left = x - hw;
        float top = y - hh;
        float right = x + hw;
        float bottom = y + hh;
        rect.set(left, top, right, bottom);
    }

    public void draw(Canvas canvas, float x, float y) {
        float hw = this.getWidth() / 2;
        float hh = this.getHeight() / 2;

        float left = x - hw;
        float top = y - hh;
        float right = x + hw;
        float bottom = y + hh;
        dstRect.set(left, top, right, bottom);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
