package kr.ac.kpu.game.s2016182010.flappyball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import kr.ac.kpu.game.s2016182010.flappyball.utill.RankingAdapter;
import kr.ac.kpu.game.s2016182010.flappyball.utill.RankingDBHelper;

public class RankingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListView lv = findViewById(R.id.lv_rank);
        RankingAdapter adapter = new RankingAdapter();
        adapter.setData(TitleActivity.dbHelper.readRanking());
        lv.setAdapter(adapter);
    }

    public void moveToTitle(View view) {
        Intent intent = new Intent(this, TitleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}