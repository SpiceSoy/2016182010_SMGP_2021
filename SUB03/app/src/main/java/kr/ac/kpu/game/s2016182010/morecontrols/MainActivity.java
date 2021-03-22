package kr.ac.kpu.game.s2016182010.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBox inCheckBox;
    private TextView outTextView;
    private EditText inEditText;
    private TextView editTextView;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            editTextView.setText("String Length = " + s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inCheckBox = findViewById(R.id.in_check_box);
        outTextView = findViewById(R.id.out_text_view);
        inEditText = findViewById(R.id.in_edit_text);
        editTextView = findViewById(R.id.edit_text_view);
        inEditText.addTextChangedListener(watcher);
    }

    public void onBtnApply(View view) {
        boolean checked = inCheckBox.isChecked();
        String text = checked ? "Using Firewall" : "Not Using Firewall";
        outTextView.setText(text);

        String user = inEditText.getText().toString();
        editTextView.setText("User Info = " + user);
    }

    public void onCheckFirewall(View view) {
        boolean checked = inCheckBox.isChecked();
        String text = checked ? "Using Firewall" : "Not Using Firewall";
        outTextView.setText(text);
    }
}