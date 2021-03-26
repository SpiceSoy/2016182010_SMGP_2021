package kr.ac.kpu.game.s2016182010.frameanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();

    // 일단은 activity = context
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas); 필요없음
        Paint paint = new Paint();
        paint.setColor(0xFF0044FF);
        Rect rect = new Rect(10,20,300,400);
        Log.d(TAG, "drawing " + rect);
        canvas.drawRect(rect, paint);
    }
}
