package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.object.ImageObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class Platform extends ImageObject implements BoxCollidable {
    private static final int UNIT_SIZE = 40;
    public enum Type {
        T_10X2,
        T_2X2,
        T_3X1;

        float width() {
            int w= 1;
            switch (this) {
                case T_10X2: w = 10;
                case T_2X2: w = 2;
                case T_3X1: w = 3;
            }
            return w * UNIT_SIZE * GameView.MULTIPLIER;
        }
        float height() {
            int h = 1;
            switch (this) {
                case T_10X2: h = 2;
                case T_2X2: h = 2;
                case T_3X1: h = 1;
            }
            return h * UNIT_SIZE * GameView.MULTIPLIER;
        }
        int resId() {
            switch (this) {
                case T_10X2: return R.mipmap.cookierun_platform_480x48;
                case T_2X2: return R.mipmap.cookierun_platform_124x120;
                case T_3X1:return R.mipmap.cookierun_platform_120x40;
            }
            return -1;
        }
    }

    public Platform(Type type, float x, float y) {
        super(type.resId(), x, y);
        float w = type.width();
        float h = type.height();
        dstRect.set(x, y, x+w, y+h);
    }

    @Override
    public void getBoundingRect(RectF rect) {

    }
}
