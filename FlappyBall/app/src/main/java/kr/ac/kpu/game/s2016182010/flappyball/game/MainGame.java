package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.flappyball.R;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;
import kr.ac.kpu.game.s2016182010.flappyball.utill.Camera;
import kr.ac.kpu.game.s2016182010.flappyball.utill.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    static MainGame instance;
    private Ball ball;
    private BlockManager blockManager;
    private ArrayList<ArrayList<GameObject>> layers;

    public enum Layer {
        bg_0,
        bg_1,
        ball,
        block,
        controller,
        fg,
        ui,
        LAYER_COUNT
    }


    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public float frameTime;
    public static final int BALL_COUNT = 10;

    private boolean initialized = false;

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public boolean initResources() {
        if (initialized) return false;
        float w = GameView.instance.getWidth();
        float h = GameView.instance.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        ball = new Ball(R.mipmap.bird1_1, w/2, h/2);
        blockManager = new BlockManager();
        add(Layer.ball, ball);
        add(Layer.controller, blockManager);
        add(Layer.bg_0, new HorizontalScrollBackground(R.mipmap.bg_night, 0.8f));
        add(Layer.bg_0, new HorizontalScrollBackground(R.mipmap.bg_star, 0.1f));
        add(Layer.bg_0, new HorizontalScrollBackground(R.mipmap.bg_cloud, 0.45f));
        for(int i = 0; i < 100; i++) {
            blockManager.addRandomBlockSet(w* 0.9f + i * w * 1.0f);
        }
        initialized = true;
        return true;
    }

    public void update() {
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }

        for (GameObject o : layers.get(Layer.block.ordinal())) {
            CollisionHelper.COL_TYPE result = CollisionHelper.getCollideDirection(ball, (Block) o);
            ball.onCollisionBlock(result);
        }
    }

    public Camera camera = new Camera();
    public void draw(Canvas canvas) {
        camera.startCamera(ball.getPositionX(), canvas);
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
        camera.endCamera(canvas);
    }

    float touchStartX;
    float touchStartY;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            touchStartX = event.getX();
            touchStartY = event.getY();
            Log.d(TAG, "start touch is " + touchStartX + ", " + touchStartY);
            return true;
        }
        else if( action == MotionEvent.ACTION_UP ) {
            Log.d(TAG, "shoot touch is " + event.getX() + ", " +  event.getY());
            ball.shoot(touchStartX, touchStartY, event.getX(), event.getY());
            return true;
        }
        return false;
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.instance.post(
                new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<GameObject> objects = layers.get(layer.ordinal());
                        objects.add(gameObject);
                    }
                }
        );
    }

    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects : layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
//                        if (gameObject instanceof Recyclable) {
//                            ((Recyclable) gameObject).recycle();
//                            recycle(gameObject);
//                        }
                        break;
                    }
                }
            }
        };

        if (delayed) {
            GameView.instance.post(runnable);
        } else {
            runnable.run();
        }
    }
}
