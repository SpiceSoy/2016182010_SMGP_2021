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

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public float frameTime;
    public static final int BALL_COUNT = 10;

    private ArrayList<GameObject> objects = new ArrayList<>();

    private boolean initialized = false;

    public boolean initResources() {
        if (initialized) return false;
        float w = GameView.instance.getWidth();
        float h = GameView.instance.getHeight();

//        player = new Player(w / 2, h - 300, 0, 0);
        ball = new Ball(R.mipmap.bird1_1, w/2, h/2);
        blockManager = new BlockManager();
        objects.add(ball);
        objects.add(blockManager);

        for(int i = 0; i < 100; i++) {
            blockManager.addRandomBlockSet(w* 0.9f + i * w * 1.0f);
        }
        initialized = true;
        return true;
    }

    public void update() {
//        if(!initialized) return;  //현재는 무의미
        for (GameObject o : objects) {
            o.update();
        }

        for (GameObject o : objects) {
            if(!(o instanceof Block)) {
                continue;
            } else{
                CollisionHelper.COL_TYPE result = CollisionHelper.getCollideDirection(ball, (Block) o);
                ball.onCollisionBlock(result);
            }
        }
    }

    Camera camera = new Camera();
    public void draw(Canvas canvas) {
        camera.startCamera(ball.getPositionX(), canvas);
        for (GameObject o : objects) {
            o.draw(canvas);
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

    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
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
