package kr.ac.kpu.game.s2016182010.cookierun.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class HorizontalScrollBackground implements GameObject {

    private final Bitmap bitmap;
    private final float speed;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private float scroll;

    public HorizontalScrollBackground(int resId, int speed) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float l = 0;
        float t = 0;
        float r = GameView.view.getWidth();
        float b = r * ((float)h / (float)w);
        dstRect.set(l, t, r, b);
        srcRect.set(0, 0, w, h);
        this.speed = speed * GameView.MULTIPLIER;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        float amount = speed * game.frameTime;
        scroll += amount;
    }

    @Override
    public void draw(Canvas canvas) {
        int vw = GameView.view.getWidth();
        int vh = GameView.view.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dw = vh * iw / ih;

        int curr = (int)scroll % dw;
        if (curr > 0) curr -= dw;

        while (curr < vw) {
            dstRect.set(curr, 0, curr + dw, vh);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            curr += dw;
        }
    }
}
