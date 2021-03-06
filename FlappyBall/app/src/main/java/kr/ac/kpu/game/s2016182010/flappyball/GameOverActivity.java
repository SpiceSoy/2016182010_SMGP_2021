package kr.ac.kpu.game.s2016182010.flappyball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameOverActivity extends AppCompatActivity {

    TextView overRank;
    EditText overName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        TextView scoreView = findViewById(R.id.score_view);
        int score = getIntent().getIntExtra("score", 0);
        scoreView.setText(score + "M");

//        overRank = findViewById(R.id.over_rank);
        overName = findViewById(R.id.over_name);
    }

    public void moveToTitle(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void moveToRanking(View view) {
        if(overName.getText().length() == 0) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        int score = getIntent().getIntExtra("score", 0);
        TitleActivity.dbHelper.addRank(String.valueOf(overName.getText()), score);
        Intent intent = new Intent(this, RankingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}