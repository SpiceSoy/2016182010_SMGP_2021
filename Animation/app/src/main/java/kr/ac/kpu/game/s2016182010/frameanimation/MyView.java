package kr.ac.kpu.game.s2016182010.frameanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint paint = new Paint();
    private Rect rect = new Rect();

    // 일단은 activity = context
    public MyView(Context context) {
        super(context);
        paint.setColor(0xFF0044FF);
    }

    public MyView(Context context, AttributeSet set) {
        super(context, set);
        paint.setColor(0xFF0044FF);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "on Touch : " + event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 교수님 쓴 함수 대신 정상 작동 함수로 수정
        int l = 0 + getPaddingLeft();
        int t = 0 + getPaddingTop();
        int w = getWidth() - getPaddingRight();
        int h = getHeight() - getPaddingBottom();
        rect.set(l, t, w, h);
        Log.d(TAG, "drawing " + rect);
        canvas.drawRect(rect, paint);
    }

}
