package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.R;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameBitmap;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class Score implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;
    private int score;
    private int displayScore;
    private Rect scoreSrc = new Rect();
    private RectF scoreDst = new RectF();

    public Score(int right, int top) {
        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
        score = 0;
    }

    @Override
    public void update() {
        score = (int)(MainGame.get().camera.getLeft() * 0.001f);
        displayScore = score;
//        if(displayScore < score) {
//            displayScore += 1;
//        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int nw = bitmap.getWidth() / 10;
        int nh = bitmap.getHeight();
        int x = right;
        float dw = nw * GameView.MULTIPLIER;
        float dh = nh * GameView.MULTIPLIER;

        canvas.save();
        canvas.translate(MainGame.get().camera.getLeft(),0);

        if(value == 0) {
            int digit = 0;
            scoreSrc.set(digit * nw, 0, (digit + 1) * nw, nh);
            scoreDst.set(x, top, x + dw, top + dh);
            canvas.drawBitmap(bitmap, scoreSrc, scoreDst, null);
        }
        while(value > 0) {
            int digit = value % 10;
            scoreSrc.set(digit * nw, 0, (digit + 1) * nw, nh);
            scoreDst.set(x, top, x + dw, top + dh);
            canvas.drawBitmap(bitmap, scoreSrc, scoreDst, null);
            x -= dw;
            value /= 10;
        }
        canvas.restore();
    }

}
