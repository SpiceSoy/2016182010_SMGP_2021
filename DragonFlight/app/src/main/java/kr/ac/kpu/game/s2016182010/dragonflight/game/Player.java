package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.Sound;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class Player implements GameObject {
    private Bitmap bitmap;
    private int imageWidth;
    private int imageHeight;
    private float speed;

    private float x;
    private float y;

    private float tx;

    public Player(float x, float y, float dx, float dy) {
        bitmap = GameBitmap.load(R.mipmap.plane_240);
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
        this.speed = 1000;
        this.x = x;
        this.y = y;
        this.tx = x;
//        this.ty = y;
//        this.dx = dx;
//        this.dy = dy;

    }

    public void moveTo(float x, float y) {
        this.tx = x;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        float dx = (tx < x ? -1 : 1) * speed * game.frameTime;
        x += dx;
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx;
        }
//        if ((dy > 0 && y > ty) || (dy < 0 && y < ty)) {
//            y = ty;
//        }
    }

    @Override
    public void draw(Canvas canvas) {
        float left = this.x - imageWidth / 2;
        float top = this.y - imageHeight / 2;
        canvas.drawBitmap(bitmap, left, top, null);
    }
}
