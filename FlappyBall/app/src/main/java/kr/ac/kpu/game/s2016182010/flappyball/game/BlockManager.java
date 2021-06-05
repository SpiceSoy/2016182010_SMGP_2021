package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;
import kr.ac.kpu.game.s2016182010.flappyball.utill.Camera;
import kr.ac.kpu.game.s2016182010.flappyball.utill.PatternParser;

public class BlockManager implements GameObject {
    float csr = GameView.instance.getWidth() * 0.9f;
    @Override
    public void update() {
        addNewBlockRoutine();
        deleteBlockRoutine();
    }

    @Override
    public void draw(Canvas canvas) {

    }
    public void addNewBlockRoutine(){
        MainGame game = MainGame.get();
        float cameraRight = game.camera.getRight() + 500.0f;
        Log.d("Map", "cameraRight : " + cameraRight + " / csr" + csr );
        if(cameraRight > csr) {
            addNewBlock();
        }
    }

    public void addNewBlock() {
        Random r = new Random();
        float w = GameView.instance.getWidth();
        if(r.nextBoolean()) {
            addPatternBlock();
        } else{
            addRandomBlockSet(csr);
            float next = w * (0.7f + 0.6f * r.nextFloat());
            csr += next;
        }
    }

    public void deleteBlockRoutine() {
        MainGame game = MainGame.get();
        float cameraLeft = game.camera.getLeft();
        ArrayList<GameObject> blocks = game.getLayer(MainGame.Layer.block);
        for (GameObject obj : blocks) {
            Block block = (Block)obj;
            if (block.getX() + block.getWidth() < cameraLeft) {
                game.remove(block, true);
            }
        }

    }

    public void addRandomBlockSet(float positionX) {
        Random r = new Random();
        Block.BLOCK_TYPE randomType = Block.BLOCK_TYPE.values()[r.nextInt(Block.BLOCK_TYPE.TYPE_COUNT.ordinal())];
        addBlockSet(positionX, GameView.instance.getHeight() * (0.2f + r.nextFloat() * 0.6f), 150 + r.nextFloat() * 150, randomType );
    }

    public void addPatternBlock() {
        ArrayList<Pattern> patterns;
        try {
            patterns = PatternParser.read();
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        Random r = new Random();
        float w = GameView.instance.getWidth();
        float h = GameView.instance.getHeight();
        Pattern randomPattern = patterns.get(r.nextInt(patterns.size()));
        for ( PatternBlock b : randomPattern.patternBlockArrayList ) {
            float center = b.center / 100.0f;
            float margin = b.margin / 100.0f;
            float next = b.next / 100.0f;
            addBlockSet(csr, center * h, margin * w, b.type );
            csr += next * w;
        }
    }

    public void addBlockSet(float positionX, float centerY, float margin, Block.BLOCK_TYPE type) {
        float hm = margin * 0.5f * GameView.MULTIPLIER;
        Block up = new Block(Block.BLOCK_POSITION.TOP, type, positionX, 0);
        float upY = centerY - (hm + up.getHeight());
        up.setY(upY);

        Block down = new Block(Block.BLOCK_POSITION.BOTTOM, type, positionX, 0);
        float downY = centerY + (hm + down.getHeight());
        down.setY(downY);

        MainGame game = MainGame.get();
        game.add(MainGame.Layer.block, up);
        game.add(MainGame.Layer.block, down);
    }
}
