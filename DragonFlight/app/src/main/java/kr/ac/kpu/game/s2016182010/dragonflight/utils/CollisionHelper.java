package kr.ac.kpu.game.s2016182010.dragonflight.utils;

import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;

public class CollisionHelper {
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();

    public static boolean collides(BoxCollidable a, BoxCollidable b) {
        a.getBoundingRect(rect1);
        b.getBoundingRect(rect2);
        if (rect1.left > rect2.right) return false;
        if (rect1.left > rect2.right) return false;
        if (rect1.left > rect2.right) return false;
        if (rect1.left > rect2.right) return false;
        return true;
    }
}
