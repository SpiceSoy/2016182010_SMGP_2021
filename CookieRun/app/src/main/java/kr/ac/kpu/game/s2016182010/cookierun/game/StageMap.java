package kr.ac.kpu.game.s2016182010.cookierun.game;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.RandomAccess;

import kr.ac.kpu.game.s2016182010.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182010.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182010.cookierun.framework.view.GameView;

public class StageMap implements GameObject {
    private static final String TAG = StageMap.class.getSimpleName();
    private final ArrayList<String> lines = new ArrayList<>();
    private int columns;
    private int rows;
    private int current;

    public StageMap(String fileName) {
        AssetManager assets = GameView.view.getContext().getAssets();
        try {
            InputStream inputStream = assets.open(fileName);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String header = br.readLine();
            String[] comps = header.split(" ");
            this.columns = Integer.parseInt(comps[0]);
            this.rows = Integer.parseInt(comps[1]);
            Log.d(TAG, "stage file load : c = " + this.columns + " r = " + this.rows);
            while(true) {
                String line = br.readLine();
                if(line == null) break;
                lines.add(line);
                Log.d(TAG, "row = " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        createColumn();
//        ArrayList<GameObject> objects = game.objectsAt(MainGame.Layer.platform);
//        float rightMax = 0;
//        for(GameObject obj : objects) {
//            Platform platform = (Platform) obj;
//            float right = platform.getRight();
//            rightMax = right > rightMax ? right : rightMax;
//        }
//        float vw = GameView.view.getWidth();
//        float vh = GameView.view.getHeight();
//        if(rightMax < vw) {
//            Log.d(TAG, "create a platform here : " + rightMax);
//            float tx = rightMax, ty = vh - Platform.Type.T_10X2.height();
//            Platform platform = new Platform(Platform.Type.Random, tx, ty);
//            game.add(MainGame.Layer.platform,platform);
//
//            Random r = new Random();
//            game.add(MainGame.Layer.item, new Jelly(r.nextInt(60), tx, r.nextInt((int)ty)));
//        }
    }

    private void createColumn() {
        if(current >= 100) return;
        float x = Platform.UNIT_SIZE * GameView.MULTIPLIER * current, y = 0;
        for (int row = 0; row < rows; row++) {
            char ch = getAt(current, row);
            y += Platform.UNIT_SIZE * GameView.MULTIPLIER;
            createObject(ch, x, y);
        }
        current++;
    }

    private void createObject(char ch, float x, float y) {
        MainGame game = (MainGame) BaseGame.get();
        if ('9' >= ch && ch >= '1') {
            Jelly item = new Jelly(ch - '1', x, y);
            game.add(MainGame.Layer.item, item);
        } else if ( 'Q' >= ch && ch >= 'O') {
            Platform platform = new Platform(Platform.Type.values()[ch - 'O'], x, y);
            game.add(MainGame.Layer.platform, platform);
        }
    }

    private char getAt(int x, int y) {
        try {
            int csrY = (x / this.columns) * this.rows + y;
            int csrX = (x % this.columns);
            return this.lines.get(csrY).charAt(csrX);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
