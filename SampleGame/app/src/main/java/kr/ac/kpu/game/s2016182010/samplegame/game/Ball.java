package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.samplegame.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Ball implements GameObject {
    private AnimationGameBitmap bitmap;

    private float x;
    private float y;

    private float dx;
    private float dy;

    private static float FRAME_RATE = 8.5f;

    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        bitmap = new AnimationGameBitmap(R.mipmap.fireball_128_24f, FRAME_RATE, 0);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        if (x < 0 || x + bitmap.getWidth() > w){
            this.dx *= -1;
        }

        if (y < 0 || y + bitmap.getHeight() > h){
            this.dy *= -1;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }
}
