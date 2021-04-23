package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class EnemyGenerator implements GameObject {
    public static final float INITIAL_SPAWN_INTERVAL = 5.0f;
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private float time;
    private float spawnInterval;

    public EnemyGenerator() {
        this.spawnInterval = INITIAL_SPAWN_INTERVAL;
        this.time = INITIAL_SPAWN_INTERVAL;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        time += game.frameTime;
        if (time >= spawnInterval) {
            generate();
            time -= spawnInterval;
        }
    }

    private void generate() {
        Log.d(TAG, "Generate Now !!");

        MainGame game = MainGame.get();
        float tenth = GameView.instance.getWidth() / 10;
        for (int i = 1; i <= 9; i+=2) {
            float x = tenth * i;
            int y = 0;
            Random r = new Random();
            int level = r.nextInt(20) + 1;
            Enemy enemy = new Enemy(level, x, y, 700);
            game.add(enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // Do Nothing
    }
}
