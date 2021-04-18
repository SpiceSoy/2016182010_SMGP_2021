package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182010.flappyball.R;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    static MainGame instance;
    private Ball ball;

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
        objects.add(ball);
        initialized = true;
        return true;
    }

    public void update() {
//        if(!initialized) return;  //현재는 무의미
        for (GameObject o : objects) {
            o.update();
        }
    }

    public void draw(Canvas canvas) {
//        if(!initialized) return;  //현재는 무의미
        for (GameObject o : objects) {
            o.draw(canvas);
        }
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
