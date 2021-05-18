package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class HorizontalScrollBackground implements GameObject {

    private Bitmap bitmap;
    private final float speedRatio;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private float scroll;

    public HorizontalScrollBackground(int resId, float speedRatio) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float l = 0;
        float t = 0;
        float r = GameView.instance.getWidth();
        float b = r * ((float)h / (float)w);
        dstRect.set(l, t, r, b);
        srcRect.set(0, 0, w, h);
        this.speedRatio = speedRatio;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.scroll = -game.camera.getLeft() * speedRatio;
    }

    @Override
    public void draw(Canvas canvas) {
        int vw = GameView.instance.getWidth();
        int vh = GameView.instance.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dw = vh * iw / ih;

        int curr = (int)scroll % dw;
        if (curr > 0) curr -= dw;
        MainGame game = MainGame.get();
        canvas.save();
        canvas.translate(game.camera.getLeft(),0);
        while (curr < vw + dw) {
            dstRect.set(curr, 0, curr + dw, vh);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            curr += dw;
        }
        canvas.restore();
    }
}
