package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.bitmap.IndexedGameBitmap;
import kr.ac.kpu.game.s2016182010.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182010.cookierun.framework.object.ImageObject;

public class Jelly extends ImageObject {
    private final IndexedGameBitmap ibmp;
    private final float x;
    private final float y;

    public Jelly(int index, float x, float y) {
        super(R.mipmap.jelly, x, y);
        ibmp = new IndexedGameBitmap(
                R.mipmap.jelly, 66, 66, 30, 2, 2
        );
        this.x = x;
        this.y = y;
        ibmp.setIndex(index);
    }

    @Override
    public void update() {
        BaseGame game = MainGame.get();
        float dx = Platform.SPEED * game.frameTime;
        dstRect.offset(-dx, 0);
        if(getRight() < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        ibmp.draw(canvas, dstRect);
    }
}
