package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class Camera {
    float lastPositionX = 0;
    public void startCamera(float centerX, Canvas canvas) {
        float cameraX = Math.max(centerX - (GameView.instance.getWidth() * 0.5f), lastPositionX);
        lastPositionX = cameraX;
        canvas.save();
        canvas.translate(-cameraX, 0);
    }

    public void endCamera(Canvas canvas) {
        canvas.restore();
    }
}
