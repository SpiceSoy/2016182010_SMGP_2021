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
    private float ty;

    private float dx;
    private float dy;
    private float angle;

    public Player(float x, float y, float dx, float dy) {
        bitmap = GameBitmap.load(R.mipmap.plane_240);
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();

        this.speed = 800;

        this.x = x;
        this.y = y;

        this.tx = 0;
        this.ty = 0;

        this.dx = dx;
        this.dy = dy;

    }

    public void moveTo(float x, float y) {
//        Sound.play(R.raw.hadouken);
//        Bullet bullet = new Bullet(this.x, this.y, x, y);
//        MainGame game = MainGame.get();
//        game.add(bullet);
//        this.tx = x;
//        this.ty = y;
//        float deltaX = x - this.x;
//        float deltaY = y - this.y;
//        this.angle = (float) Math.atan2(deltaY, deltaX);
//        MainGame game = MainGame.get();
//        float move_dist = speed * game.frameTime;
//        dx = (float) (move_dist * Math.cos(angle));
//        dy = (float) (move_dist * Math.sin(angle));
    }

    @Override
    public void update() {
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx;
        }
        if ((dy > 0 && y > ty) || (dy < 0 && y < ty)) {
            y = ty;
        }
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void draw(Canvas canvas) {
        float left = this.x - imageWidth / 2;
        float top = this.y - imageHeight / 2;
//        float degree = (float) Math.toDegrees(this.angle) + 90.0f;
//        canvas.save();
//        canvas.rotate(degree, x, y);
        canvas.drawBitmap(bitmap, left, top, null);
//        canvas.restore();
    }
}
