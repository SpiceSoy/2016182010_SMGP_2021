package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.framework.Recyclable;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016182010.dragonflight.utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    static MainGame instance;
    private Player player;
    private Score score;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public float frameTime;

    private ArrayList<ArrayList<GameObject>> layers;

    private static HashMap<Class, ArrayList<GameObject>> recyclePool = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recyclePool.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recyclePool.put(clazz, array);
        }
        array.add(object);
    }

    public GameObject get(Class clazz) {
        ArrayList<GameObject> array = recyclePool.get(clazz);
        if (array == null || array.isEmpty()) return null;
        return array.remove(0);
    }

    private boolean initialized = false;


    public enum Layer {
        bg_1,
        enemy,
        bullet,
        player,
        controller,
        bg_2,
        ui,
        LAYER_COUNT
    }

    public boolean initResources() {
        if (initialized) return false;
        float w = GameView.view.getWidth();
        float h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(w / 2, h - 300);
        add(Layer.player, player);
        add(Layer.controller, new EnemyGenerator());

        int margin = (int) (40 * GameView.MULTIPLIER);

        score = new Score((int) w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        HorizontalScrollBackground bg = new HorizontalScrollBackground(R.mipmap.bg_city, 10);
        add(Layer.bg_1, bg);

        HorizontalScrollBackground clouds = new HorizontalScrollBackground(R.mipmap.clouds, 20);
        add(Layer.bg_2, clouds);

        initialized = true;
        return true;
    }

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }

        ArrayList<GameObject> enemies = layers.get(Layer.enemy.ordinal());
        ArrayList<GameObject> bullets = layers.get(Layer.bullet.ordinal());

        for (GameObject o1 : enemies) {
            Enemy enemy = (Enemy) o1;
            boolean collided = false;
            for (GameObject o2 : bullets) {
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    remove(enemy, false);
                    remove(bullet, false);
                    score.addScore(10);
                    collided = true;
                    break;
                }
            }
            if (collided) {
                break;
            }
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
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

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(
                new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<GameObject> objects = layers.get(layer.ordinal());
                        objects.add(gameObject);
                    }
                }
        );
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }

    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects : layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof Recyclable) {
                            ((Recyclable) gameObject).recycle();
                            recycle(gameObject);
                        }
                        break;
                    }
                }
            }
        };

        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }
}
