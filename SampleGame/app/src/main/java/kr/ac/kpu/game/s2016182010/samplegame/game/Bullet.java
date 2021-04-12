package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private final float radius;

    private float x;
    private float y;

    private float dx;
    private float dy;

    private AnimationGameBitmap bitmap;
    private static float FRAME_RATE = 8.5f;
    private final float angle;

    public Bullet(float x, float y, float tx, float ty) {
        this.radius = 10.0f;

        this.x = x;
        this.y = y;

        float speed = 1000.0f;

        float deltaX = tx - this.x;
        float deltaY = ty - this.y;
        angle = (float) Math.atan2(deltaY, deltaX);
        float move_dist = speed;
        dx = (float) (move_dist * Math.cos(angle));
        dy = (float) (move_dist * Math.sin(angle));

        bitmap = new AnimationGameBitmap(R.mipmap.bullet_hadoken, FRAME_RATE, 6 );
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        boolean toBeDeleted = false;

        if (x < 0 || x + bitmap.getWidth() > w){
            toBeDeleted = true;
        }

        if (y < 0 || y + bitmap.getHeight() > h){
            toBeDeleted = true;
        }
        if(toBeDeleted){
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y, (float)Math.toDegrees(angle) + 90.0f);
    }
}
