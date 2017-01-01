package com.example.vladimir.customslider.custom_view;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.example.vladimir.customslider.R;
import com.example.vladimir.customslider.item_states.State;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class IndicatorContainer
        extends RelativeLayout implements IIndicatorContainer, ViewPager.OnPageChangeListener {

    private int activeColor;
    private int inActiveColor;
    private int quantity = 3;
    private int activeItemPx;
    private int inActiveItemPx;
    private int numberActive;

    public IndicatorContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Indicators);
        quantity = typedArray.getInt(R.styleable.Indicators_indicators, quantity);
        activeColor = typedArray.getColor(R.styleable.Indicators_active_color,
                ContextCompat.getColor(context, R.color.colorPrimaryDark));
        inActiveColor = typedArray.getColor(R.styleable.Indicators_inactive_color,
                ContextCompat.getColor(getContext(), R.color.colorPrimary));
        activeItemPx = typedArray.getDimensionPixelSize(R.styleable.Indicators_active_item, 0);
        inActiveItemPx = typedArray.getDimensionPixelSize(R.styleable.Indicators_inactive_item, 0);
        numberActive = typedArray.getInt(R.styleable.Indicators_number_active, numberActive);

        typedArray.recycle();

        attachViews();
        animateIndicatorsLikeBounce(getRootView());
    }


    @Override
    public void attachViews() {
        clearIndicators();
        final int constantMargin = 0b110010;

        for (int i = 0; i < quantity; i++) {
            Indicators indicator = new Indicators(getContext());
            indicator.setState(changeState(i));
            indicator.setActiveColor(activeColor);
            indicator.setInActiveColor(inActiveColor);
            indicator.setActiveItemPx(activeItemPx);
            indicator.setInActiveItemPx(inActiveItemPx);

            int leftMargin = i * (constantMargin + inActiveItemPx);
            int maxDim = Math.max(activeItemPx, inActiveItemPx);
            RelativeLayout.LayoutParams params = new LayoutParams(maxDim, WRAP_CONTENT);
            params.setMargins(leftMargin, 0, 0, 0);
            indicator.setLayoutParams(params);
            addView(indicator);
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        attachViews();
    }

    private void clearIndicators() {
        removeAllViews();
    }


    private void animateIndicatorsLikeBounce(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "y", 0.0f, 90f);
        animation.setDuration(1000);
        animation.setInterpolator(new BounceInterpolator());
        animation.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeActiveIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
