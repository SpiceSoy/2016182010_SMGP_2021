package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016182010.dragonflight.utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    static MainGame instance;
    private Player player;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public float frameTime;

    private ArrayList<GameObject> objects = new ArrayList<>();

    private boolean initialized = false;

    public boolean initResources() {
        if (initialized) return false;
        float w = GameView.instance.getWidth();
        float h = GameView.instance.getHeight();

        player = new Player(w / 2, h - 300);
        objects.add(player);

        objects.add(new EnemyGenerator());

        initialized = true;
        return true;
    }

    public void update() {
        for (GameObject o : objects) {
            o.update();
        }
        for (GameObject o1 : objects) {
            if(!(o1 instanceof Enemy)){
                continue;
            }
            boolean removed = false;
            for (GameObject o2 : objects){
                if(!(o2 instanceof Bullet)){
                    continue;
                }

                if(CollisionHelper.collides((BoxCollidable) o1, (BoxCollidable)o2)) {
                    Log.d(TAG, "Collision : " + o1 + " - " + o2);
                    remove(o1);
                    remove(o2);
                    removed = true;
                    break;
                }
            }

            if(removed){
                continue;
            }

            if(CollisionHelper.collides((BoxCollidable) o1, player)) {
                Log.d(TAG, "Collision : Enemy - Player");
            }
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject o : objects) {
            o.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }

    public void add(GameObject gameObject) {
        GameView.instance.post(
                new Runnable() {
                    @Override
                    public void run() {
                        objects.add(gameObject);
                    }
                }
        );
    }

    public void remove(GameObject gameObject) {
        GameView.instance.post(
                new Runnable() {
                    @Override
                    public void run() {
                        objects.remove(gameObject);
                    }
                }
        );
    }
}
