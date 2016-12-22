package com.example.vladimir.customslider;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.vladimir.customslider.item_states.State;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class IndicatorContainer<T extends Indicators>
        extends RelativeLayout implements IIndicatorContainer {

    private List<WeakReference<T>> indicators;
    private int activeColor;
    private int inActiveColor;
    private int count = 3;
    private int activeItemPx;
    private int inActiveItemPx;
    private int numberActive;

    public IndicatorContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Indicators);
        count = typedArray.getInt(R.styleable.Indicators_indicator, count);
        activeColor = typedArray.getColor(R.styleable.Indicators_active_color,
                ContextCompat.getColor(context, R.color.colorPrimaryDark));
        inActiveColor = typedArray.getColor(R.styleable.Indicators_inactive_color,
                ContextCompat.getColor(getContext(), R.color.colorPrimary));
        activeItemPx = typedArray.getDimensionPixelSize(R.styleable.Indicators_active_item, 0);
        inActiveItemPx = typedArray.getDimensionPixelSize(R.styleable.Indicators_inactive_item, 0);
        numberActive = typedArray.getInt(R.styleable.Indicators_number_active, numberActive);

        indicators = new CopyOnWriteArrayList<>();
        typedArray.recycle();

        setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        setGravity(Gravity.CENTER_HORIZONTAL);
        attachViews();
    }


    @Override
    public void attachViews() {
        clearIndicators();
        int leftMargin = 20;
        final int transitionView = 50;

        for (int i = 0; i < count; i++) {
            Indicators indicator = new Indicators(getContext());
            indicator.setState(changeState(i));
            indicator.setActiveColor(activeColor);
            indicator.setInActiveColor(inActiveColor);
            indicator.setActiveItemPx(activeItemPx);
            indicator.setInActiveItemPx(inActiveItemPx);

            leftMargin += transitionView;

            LayoutParams params = new LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(leftMargin, 0, 0, 0);
            indicator.setLayoutParams(params);
            addView(indicator);
            indicators.add(new WeakReference<>((T) indicator));
        }
    }

    private State changeState(int index) {
        return numberActive == index ?
                State.ACTIVE : State.INACTIVE;
    }

    public void changeActiveIndicator(int value) {
        numberActive = value;
        attachViews();
    }

    public int getCount() {
        return count;
    }

    private void clearIndicators() {
        indicators.clear();
        removeAllViews();
//        animateCircle();
    }


    private void animateCircle() {
        final ValueAnimator animator = ValueAnimator.ofInt(0, activeItemPx);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                activeItemPx = (int) animator.getAnimatedValue();
            }
        });


        animator.start();

    }
}
