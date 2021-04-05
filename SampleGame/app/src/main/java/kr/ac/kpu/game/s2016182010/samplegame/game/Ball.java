package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182010.samplegame.R;
import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class Ball implements GameObject {
    private static Bitmap bitmap;
    private static int imageWidth;
    private static int imageHeight;

    private float x;
    private float y;

    private float dx;
    private float dy;

    public Ball(float x, float y, float dx, float dy) {
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(
                    GameView.instance.getResources(),
                    R.mipmap.soccer_ball_240
            );
            Ball.imageWidth = bitmap.getWidth();
            Ball.imageHeight = bitmap.getHeight();
        }

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;

        int w = GameView.instance.getWidth();
        int h = GameView.instance.getHeight();

        if (x < 0 || x + Ball.imageWidth > w){
            this.dx *= -1;
        }

        if (y < 0 || y + Ball.imageHeight > h){
            this.dy *= -1;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.x, this.y, null);
    }
}
