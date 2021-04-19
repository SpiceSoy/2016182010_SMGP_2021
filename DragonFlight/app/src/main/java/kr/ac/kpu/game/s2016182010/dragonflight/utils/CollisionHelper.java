package kr.ac.kpu.game.s2016182010.dragonflight.utils;

import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;

public class CollisionHelper {
    public static boolean collides(BoxCollidable a, BoxCollidable b) {
        RectF r1 = a.getBoundingRect();
        RectF r2 = b.getBoundingRect();
        if (r1.left > r2.right) return false;
        if (r1.left > r2.right) return false;
        if (r1.left > r2.right) return false;
        if (r1.left > r2.right) return false;
        return true;
    }
}
