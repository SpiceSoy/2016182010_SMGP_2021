package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private static int imageWidth;
    private static int imageHeight;
    private final float radius;

    private float x;
    private float y;

    private float dx;
    private float dy;

    private static Bitmap bitmap;
    private static float FRAME_RATE = 8.5f;
    private final long createdOn;

    private int frameIndex;
    private final float angle;

    public Bullet(float x, float y, float tx, float ty) {
        this.radius = 10.0f;

        this.x = x;
        this.y = y;

        float speed = 1000.0f;

        float deltaX = tx - this.x;
        float deltaY = ty - this.y;
        angle = (float) Math.atan2(deltaY, deltaX);
        MainGame game = MainGame.get();
        float move_dist = speed;
        dx = (float) (move_dist * Math.cos(angle));
        dy = (float) (move_dist * Math.sin(angle));

        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(
                    GameView.instance.getResources(),
                    R.mipmap.laser_light
            );
            Bullet.imageWidth = bitmap.getWidth();
            Bullet.imageHeight = bitmap.getHeight();
        }
        createdOn = System.currentTimeMillis();
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        boolean toBeDeleted = false;

        int elapsed = (int) (System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * FRAME_RATE) % 10;

        if (x < 0 || x + Bullet.imageWidth / 10 > w){
            toBeDeleted = true;
        }

        if (y < 0 || y + Bullet.imageHeight > h){
            toBeDeleted = true;
        }
        if(toBeDeleted){
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int hw = 100;
        int hh = 124;
        int fw = imageWidth / 10;
        int fh = imageHeight;
        Rect src = new Rect(
                (fw * frameIndex), 0, (fw * (frameIndex + 1)), fh
        );
        RectF dst = new RectF(
                this.x - hw,this.y - hh, this.x + hw, this.y + hh
        );

        float degree = (float)Math.toDegrees(this.angle) + 90.0f;

        canvas.save();
        canvas.rotate(degree, x, y);
        canvas.drawBitmap(bitmap, src, dst, null);
        canvas.restore();
    }
}
