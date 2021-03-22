package kr.ac.kpu.game.s2016182010.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView imgView;
    private ImageButton prevBtn;
    private ImageButton nextBtn;
    private static int[] catIds = {
            R.mipmap.cat1,
            R.mipmap.cat2,
            R.mipmap.cat3,
            R.mipmap.cat4,
            R.mipmap.cat5
    };
    private int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = findViewById(R.id.helloWorldText);
        imgView = findViewById(R.id.imgView);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        setBtnState();
        showImage();
    }

    private void setBtnState() {
        prevBtn.setEnabled(imageIndex != 0);
        nextBtn.setEnabled(imageIndex != (catIds.length - 1));
    }

    private void showImage() {
        mainTextView.setText((imageIndex + 1) + " / " + this.catIds.length);
        imgView.setImageResource(catIds[imageIndex]);
    }

    public void onPrevButtonClick(View v) {
        imageIndex = Math.min(Math.max(imageIndex - 1, 0), catIds.length - 1);
        setBtnState();
        showImage();
    }

    public void onNextButtonClick(View view) {
        imageIndex = Math.min(Math.max(imageIndex + 1, 0), catIds.length - 1);
        setBtnState();
        showImage();
    }

}