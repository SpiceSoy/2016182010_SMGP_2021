package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.cookierun.R;
import kr.ac.kpu.game.s2016182010.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.object.HorizontalScrollBackground;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class MainGame extends BaseGame {
    private Player player;
    private Score score;
    private boolean initialized = false;



    public enum Layer {
        bg,
        platform,
        player,
        item,
        ui,
        controller,
        LAYER_COUNT;
    }
    @Override
    public boolean initResources() {
        if (initialized) return false;
        float w = GameView.view.getWidth();
        float h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(200, h - Platform.Type.T_2X2.height() - 250);
        add(Layer.player, player);

        int margin = (int) (40 * GameView.MULTIPLIER);

        score = new Score((int) w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_1, -10));
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_2, -50));
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.cookie_run_bg_3, -100));

        add(Layer.controller, new StageMap("stage_01.txt"));

//        float tx = 0, ty = h - Platform.Type.T_2X2.height();
//        while (tx < w) {
//            Platform platform = new Platform(Platform.Type.Random, tx, ty);
//            add(Layer.platform, platform);
//            tx += platform.getDestWidth();
//        }

        initialized = true;
        return true;
    }

    @Override
    public void update() {
        super.update();
    }

    public ArrayList<GameObject> objectsAt(Layer platform) {
        return objectsAt(platform.ordinal());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
//            player.moveTo(event.getX(), event.getY());
            player.jump();
            return true;
        }
        return false;
    }

    public void add(Layer layer, GameObject gameObject) {
        super.add(layer.ordinal(), gameObject);
    }
}
