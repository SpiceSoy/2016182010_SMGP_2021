package kr.ac.kpu.game.s2016182010.flappyball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        TextView scoreView = findViewById(R.id.score_view);
        int score = getIntent().getIntExtra("score", 0);
        scoreView.setText(score + "M");
    }
}