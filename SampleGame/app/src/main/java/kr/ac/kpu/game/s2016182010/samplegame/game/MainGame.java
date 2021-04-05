package kr.ac.kpu.game.s2016182010.samplegame.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182010.samplegame.framework.GameObject;

public class MainGame {
    static MainGame instance;
    public static MainGame get(){
        if(instance == null){
            instance = new MainGame();
        }
        return instance;
    }

    public float frameTime;
    public static final int BALL_COUNT = 10;

    private ArrayList<GameObject> objects = new ArrayList<>();
    private Player player;


    public void initResources() {
        player = new Player(100,100,0,0);
        Random rand = new Random();
        for (int i = 0; i < BALL_COUNT; ++i) {
            float x = rand.nextInt(1000);
            float y = rand.nextInt(1000);
            float dx = rand.nextFloat() * 1000 - 500;
            float dy = rand.nextFloat() * 1000 - 500;
            objects.add(new Ball(x, y, dx, dy));
        }
        objects.add(player);
    }

    public void update() {
        for (GameObject o : objects) {
            o.update();
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject o : objects){
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
}
