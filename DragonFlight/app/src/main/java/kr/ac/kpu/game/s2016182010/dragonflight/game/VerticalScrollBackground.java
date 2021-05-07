package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class VerticalScrollBackground implements GameObject {

    private final Bitmap bitmap;
    private final float speed;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    public VerticalScrollBackground(int resId, int speed) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float l = 0;
        float t = 0;
        float r = GameView.instance.getWidth();
        float b = r * ((float)h / (float)w);
        dstRect.set(l, t, r, b);
        srcRect.set(0, 0, w, h);
        this.speed = speed * GameView.MULTIPLIER;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        dstRect.top += speed * game.frameTime;
        dstRect.bottom += speed * game.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
