package com.example.vladimir.customslider;

import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IndicatorContainer indicatorContainer = (IndicatorContainer) findViewById(R.id.custom_view);
        Button nextIndicator = (Button) findViewById(R.id.button);


        nextIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == indicatorContainer.getCount())
                    counter = 0;
                indicatorContainer.changeActiveIndicator(counter);
                counter++;
            }
        });
    }
}
