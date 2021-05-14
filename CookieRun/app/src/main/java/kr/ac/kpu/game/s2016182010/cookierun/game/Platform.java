package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.object.ImageObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class Platform extends ImageObject implements BoxCollidable {

    enum Type {
        T_10X2,
        T_2X2,
        T_3X1
    }

    static final int[] TYPE_RES_ID = {
            R.mipmap.cookierun_platform_480x48,
            R.mipmap.cookierun_platform_124x120,
            R.mipmap.cookierun_platform_120x40
    };

    static final float UNIT = 40 * GameView.MULTIPLIER;
    static final float[] TYPE_SIZE_X_UNIT = {
        10, 2, 3
    };

    static final float[] TYPE_SIZE_Y_UNIT = {
        2, 2, 1
    };

    public Platform(Type type, float x, float y) {
        super(TYPE_RES_ID[type.ordinal()], x, y);
        float w = UNIT * TYPE_SIZE_X_UNIT[type.ordinal()];
        float h = UNIT * TYPE_SIZE_Y_UNIT[type.ordinal()];
        dstRect.set(x, y, x+w, y+h);
    }

    @Override
    public void getBoundingRect(RectF rect) {

    }
}
