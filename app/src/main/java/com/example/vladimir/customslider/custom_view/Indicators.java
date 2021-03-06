package com.example.vladimir.customslider.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.vladimir.customslider.item_states.State;

public class Indicators extends View {
    private Paint paint = new Paint();
    private int radius;
    private int activeColor;
    private int inActiveColor;
    private int activeItemPx;
    private int inActiveItemPx;
    private State state;

    public Indicators(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        int width;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = Math.min(widthSize + widthSize, widthSize);
        } else {
            width = widthSize + widthSize;
        }

        setMeasuredDimension(width, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x = state == State.INACTIVE ? inActiveItemPx : activeItemPx;
        int y = state == State.INACTIVE ? inActiveItemPx : activeItemPx;
        radius = y;

        paint.setColor(getStateColor());
        canvas.drawCircle(x, y, radius, paint);
    }



    private int getStateColor() {
        return state == State.ACTIVE ?
                activeColor : inActiveColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
        invalidate();
    }

    public void setInActiveColor(int inActiveColor) {
        this.inActiveColor = inActiveColor;
    }

    public void setActiveItemPx(int activeItemPx) {
        this.activeItemPx = activeItemPx;
        invalidate();
    }

    public void setInActiveItemPx(int inActiveItemPx) {
        this.inActiveItemPx = inActiveItemPx;
        invalidate();
    }

    public void setState(State state) {
        this.state = state;
        invalidate();
    }


}
