package kr.ac.kpu.game.s2016182010.samplegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import kr.ac.kpu.game.s2016182010.samplegame.ui.view.GameView;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();

    protected static Bitmap load(int resId) {
        Bitmap bitmap;
        if (bitmaps.containsKey(resId)) {
            bitmap = bitmaps.get(resId);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Resources res = GameView.instance.getResources();
            bitmap = BitmapFactory.decodeResource(res, resId, options);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }
}
