package kr.ac.kpu.game.s2016182010.cookierun.framework.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.Recyclable;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class BaseGame {
    private static final String TAG = BaseGame.class.getSimpleName();
    private static HashMap<Class, ArrayList<GameObject>> recyclePool = new HashMap<>();
    private static BaseGame instance;

    private ArrayList<ArrayList<GameObject>> layers;

    public float frameTime;

    protected BaseGame() {
        instance = this;
    }

    public static BaseGame get() {
        return instance;
    }

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

    public boolean initResources() {
        return false;
    }

    protected void initLayers(int layerCount) {
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
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void add(int layerIndex, GameObject gameObject) {
        GameView.view.post(
                new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<GameObject> objects = layers.get(layerIndex);
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
