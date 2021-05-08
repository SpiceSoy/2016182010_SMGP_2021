package kr.ac.kpu.game.s2016182010.dragonflight.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();

    public static Bitmap load(int resId) {
        Bitmap bitmap;
        if (bitmaps.containsKey(resId)) {
            bitmap = bitmaps.get(resId);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Resources res = GameView.view.getResources();
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

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public void getBoundingRect(float x, float y, RectF rect){
        float hw = this.getWidth() / 2;
        float hh = this.getHeight() / 2;

        float left = x - hw * GameView.MULTIPLIER;
        float top = y - hh * GameView.MULTIPLIER;
        float right = x + hw * GameView.MULTIPLIER;
        float bottom = y + hh * GameView.MULTIPLIER;
        rect.set(left, top, right, bottom);
    }

    public void draw(Canvas canvas, float x, float y) {
        float hw = this.getWidth() / 2;
        float hh = this.getHeight() / 2;

        float left = x - hw * GameView.MULTIPLIER;
        float top = y - hh * GameView.MULTIPLIER;
        float right = x + hw * GameView.MULTIPLIER;
        float bottom = y + hh * GameView.MULTIPLIER;
        dstRect.set(left, top, right, bottom);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
