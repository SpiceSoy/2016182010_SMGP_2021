package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

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

    private Paint paint;

    public Bullet(float x, float y, float tx, float ty) {
        this.radius = 10.0f;

        this.x = x;
        this.y = y;

        float speed = 1000.0f;

        float deltaX = tx - this.x;
        float deltaY = ty - this.y;
        float angle = (float) Math.atan2(deltaY, deltaX);
        MainGame game = MainGame.get();
        float move_dist = speed;
        dx = (float) (move_dist * Math.cos(angle));
        dy = (float) (move_dist * Math.sin(angle));

        paint = new Paint();
        paint.setColor(0xFFFF0000);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        if (x < 0 || x + Bullet.imageWidth > w){
            this.dx *= -1;
        }

        if (y < 0 || y + Bullet.imageHeight > h){
            this.dy *= -1;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
