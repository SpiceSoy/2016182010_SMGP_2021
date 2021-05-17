package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.object.ImageObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class Platform extends ImageObject implements BoxCollidable {
    private static final String TAG = Platform.class.getSimpleName();
    private static final int UNIT_SIZE = 40;
    public static final int SPEED = 350;


    public enum Type {
        T_10X2,
        T_2X2,
        T_3X1,
        Random;


        float width() {
            int w = 0;
            switch (this) {
                case T_10X2: w = 10; break;
                case T_2X2: w = 2;break;
                case T_3X1: w = 3;break;
            }
            return w * UNIT_SIZE * GameView.MULTIPLIER;
        }
        float height() {
            int h = 1;
            switch (this) {
                case T_10X2: h = 2;break;
                case T_2X2: h = 2;break;
                case T_3X1: h = 1;break;
            }
            return h * UNIT_SIZE * GameView.MULTIPLIER;
        }
        int resId() {
            switch (this) {
                case T_10X2: return R.mipmap.cookierun_platform_480x48;
                case T_2X2: return R.mipmap.cookierun_platform_124x120;
                case T_3X1:return R.mipmap.cookierun_platform_120x40;
            }
            Log.e(TAG, "This is must be not called");
            return -1;
        }
    }

    public Platform(Type type, float x, float y) {
        if(type == Type.Random) {
            Random r = new Random();
            type = r.nextBoolean() ? Type.T_10X2 : Type.T_2X2;
        }
        init(type.resId(), x, y);
        float w = type.width();
        float h = type.height();
        dstRect.set(x, y, x+w, y+h);
    }

    @Override
    public void getBoundingRect(RectF rect) {

    }

    @Override
    public void update() {
        BaseGame game = MainGame.get();
        float dx = SPEED * game.frameTime;
        dstRect.offset(-dx, 0);
        if(getRight() < 0) {
            game.remove(this);
        }
    }
}
