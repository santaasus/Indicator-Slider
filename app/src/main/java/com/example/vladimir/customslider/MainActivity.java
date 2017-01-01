package com.example.vladimir.customslider;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vladimir.customslider.custom_view.IndicatorContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IndicatorContainer indicatorContainer = (IndicatorContainer) findViewById(R.id.custom_view);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.indicator_pager);
        final FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        indicatorContainer.setQuantity(pagerAdapter.getCount());
        viewPager.addOnPageChangeListener(indicatorContainer);
    }
}
