package kr.ac.kpu.game.s2016182010.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

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
        float sixth = GameView.instance.getWidth() / 6;
        for (int i = 1; i <= 5; ++i) {
            float x = sixth * i;
            int y = 0;
            Enemy enemy = new Enemy(x, y, 700);
            game.add(enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // Do Nothing
    }
}
