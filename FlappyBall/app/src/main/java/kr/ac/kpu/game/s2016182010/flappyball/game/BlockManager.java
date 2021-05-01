package kr.ac.kpu.game.s2016182010.flappyball.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameObject;

public class BlockManager implements GameObject {
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void addBlockSet(float positionX, float centerY, float margin) {
        float hm = margin * 0.5f;
        Block up = new Block(Block.BLOCK_POSITION.TOP, Block.BLOCK_TYPE.NORMAL, positionX, 0);
        float upY = centerY - (hm + up.getHeight());
        up.setY(upY);

        Block down = new Block(Block.BLOCK_POSITION.BOTTOM, Block.BLOCK_TYPE.NORMAL, positionX, 0);
        float downY = centerY + (hm + down.getHeight());
        down.setY(downY);

        MainGame game = MainGame.get();
        game.add(up);
        game.add(down);
    }
}
