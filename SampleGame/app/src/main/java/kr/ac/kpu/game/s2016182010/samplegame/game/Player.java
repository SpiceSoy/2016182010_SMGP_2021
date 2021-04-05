package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Player implements GameObject {
    private static Bitmap bitmap;
    private static int imageWidth;
    private static int imageHeight;

    private float speed;

    private float x;
    private float y;

    private float tx;
    private float ty;

    private float dx;
    private float dy;
    private float angle;

    public Player(float x, float y, float dx, float dy) {
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(
                    GameView.instance.getResources(),
                    R.mipmap.plane_240
            );
            Player.imageWidth = bitmap.getWidth();
            Player.imageHeight = bitmap.getHeight();
        }

        this.speed = 800;

        this.x = x;
        this.y = y;

        this.tx = 0;
        this.ty = 0;

        this.dx = dx;
        this.dy = dy;
    }

    public void moveTo(float x, float y) {
        Bullet bullet = new Bullet(this.x, this.y, x, y);
        MainGame game = MainGame.get();
        game.add(bullet);
//        this.tx = x;
//        this.ty = y;
//        float deltaX = tx - this.x;
//        float deltaY = ty - this.y;
//        angle = (float) Math.atan2(deltaY, deltaX);
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
        float left = this.x - Player.imageWidth / 2;
        float top = this.y - Player.imageHeight / 2;
        canvas.drawBitmap(bitmap, left, top, null);
    }
}
