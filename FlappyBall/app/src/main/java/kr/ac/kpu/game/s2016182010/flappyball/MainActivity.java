package kr.ac.kpu.game.s2016182010.flappyball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import kr.ac.kpu.game.s2016182010.flappyball.framework.GameView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameView.instance.setTag(this);
    }

    public void changeGameOver(int score) {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}