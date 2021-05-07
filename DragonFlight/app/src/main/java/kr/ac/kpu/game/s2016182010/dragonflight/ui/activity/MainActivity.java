package kr.ac.kpu.game.s2016182010.dragonflight.ui.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kpu.game.s2016182010.dragonflight.R;
import kr.ac.kpu.game.s2016182010.dragonflight.game.MainGame;
import kr.ac.kpu.game.s2016182010.dragonflight.ui.view.GameView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d(TAG, "Density: " + metrics.density + " DPI:" + metrics.densityDpi);
        GameView.MULTIPLIER = metrics.density * 0.762f;
    }
}