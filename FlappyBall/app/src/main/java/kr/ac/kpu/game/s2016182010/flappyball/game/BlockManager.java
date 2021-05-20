package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;
import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class BlockManager implements GameObject {
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void addRandomBlockSet(float positionX) {
        Random r = new Random();
        Block.BLOCK_TYPE randomType = Block.BLOCK_TYPE.values()[r.nextInt(Block.BLOCK_TYPE.TYPE_COUNT.ordinal())];
        addBlockSet(positionX, GameView.instance.getHeight() * (0.2f + r.nextFloat() * 0.6f), 150 + r.nextFloat() * 150, randomType );
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
