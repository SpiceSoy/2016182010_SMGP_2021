package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.flappyball.framework.BoxCollide;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();

    public static boolean collides(BoxCollide a, BoxCollide b) {
        a.getBoundingRect(rect1);
        b.getBoundingRect(rect2);
        if (rect1.left > rect2.right) return false;
        if (rect1.top > rect2.bottom) return false;
        if (rect1.right < rect2.left) return false;
        if (rect1.bottom < rect2.top) return false;
        return true;
    }

    public enum COL_TYPE {
        NONE,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    public static COL_TYPE getCollideDirection(BoxCollide main, BoxCollide other) {
        main.getBoundingRect(rect1);
        other.getBoundingRect(rect2);

        if (CollisionHelper.collides(main, other)) {
            float cx = rect1.centerX();
            float cy = rect1.centerY();

            if(rect2.top <= cy && cy <= rect2.bottom){
                float ocx = rect2.centerX();
                if(cx > ocx) {
                    return COL_TYPE.RIGHT;
                } else {
                    return COL_TYPE.LEFT;
                }
            } else {
                float ocy = rect2.centerY();
                if(cy > ocy) {
                    return COL_TYPE.BOTTOM;
                } else {
                    return COL_TYPE.TOP;
                }
            }
        }
        return COL_TYPE.NONE;
    }
}

