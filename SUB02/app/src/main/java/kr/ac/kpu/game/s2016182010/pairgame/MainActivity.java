package kr.ac.kpu.game.s2016182010.pairgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] buttonIDs = {
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33,
            R.id.card_40, R.id.card_41, R.id.card_42, R.id.card_43
    };
    private int[] cards = {
            R.mipmap.card_0, R.mipmap.card_0, R.mipmap.card_5, R.mipmap.card_5,
            R.mipmap.card_1, R.mipmap.card_1, R.mipmap.card_6, R.mipmap.card_6,
            R.mipmap.card_2, R.mipmap.card_2, R.mipmap.card_7, R.mipmap.card_7,
            R.mipmap.card_3, R.mipmap.card_3, R.mipmap.card_8, R.mipmap.card_8,
            R.mipmap.card_4, R.mipmap.card_4, R.mipmap.card_9, R.mipmap.card_9,
    };

    private ImageButton prevButton;
    private int visibleCardCount = 0;
    private TextView scoreTextView;
    private int flips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.scoreTextView);
        setFlips(flips);
        startGame();
    }


    public void onBtnCard(View view) {
        int prevCard = 0;
        if(prevButton == view)
        {
            int color = getResources().getColor(R.color.purple_700);
            scoreTextView.setTextColor(color);
            return;
        }
        int color = getResources().getColor(R.color.gray);
        scoreTextView.setTextColor(color);
        if(prevButton != null)
        {
            prevButton.setImageResource(R.mipmap.card_back);
            prevCard = (Integer) prevButton.getTag();
        }
        int buttonIndex = getButtonIndex(view.getId());
        Log.d(TAG, "onBtnCard() has been called By ID = " + view.getId() + ", Index = " + buttonIndex);
        int card = cards[buttonIndex];
        ImageButton imageButton = (ImageButton) view;
        imageButton.setImageResource(card);

        if(card == prevCard) {
            prevButton.setVisibility(View.INVISIBLE);
            imageButton.setVisibility(View.INVISIBLE);
            visibleCardCount -= 2;
            prevButton = null;
            if (visibleCardCount == 0) {
                askRestart();
            }
            return;
        }
        if(prevButton != null) {
            setFlips(flips+1);
        }
        prevButton = imageButton;


    }

    public void onBtnRestart(View view) {
        askRestart();
    }

    private void askRestart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.setTitle(R.string.restart_dialog_title)
                .setMessage(R.string.restart_dialog_msg)
                .setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                    }
                })
                .setNegativeButton(R.string.common_no, null)
                .create();
        alert.show();
    }

    private void setFlips(int flips) {
        this.flips = flips;
        scoreTextView.setText("Flips : " + flips);
    }

    private void startGame() {
        Random random = new Random();
        for (int i = 0; i < cards.length; ++i){
            int ri = random.nextInt(cards.length);
            int t = cards[i];
            cards[i] = cards[ri];
            cards[ri] = t;
        }

        for (int i = 0; i < buttonIDs.length; ++i) {
            ImageButton b = findViewById(buttonIDs[i]);
            b.setTag(cards[i]);
            b.setVisibility(View.VISIBLE);
            b.setImageResource(R.mipmap.card_back);
        }
        prevButton = null;
        visibleCardCount = buttonIDs.length;
        setFlips(0);
    }


    private int getButtonIndex(int resId) {
        for (int i = 0; i < buttonIDs.length; ++i) {
            if (buttonIDs[i] == resId) {
                return i;
            }
        }
        return -1;
    };
}
