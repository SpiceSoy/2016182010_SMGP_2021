package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.kpu.game.s2016182010.dragonflight.framework.GameObject;

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
        Enemy enemy = new Enemy(500, 0,700);
        MainGame game = MainGame.get();
        game.add(enemy);
    }

    @Override
    public void draw(Canvas canvas) {
        // Do Nothing
    }
}
