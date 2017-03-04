package com.example.rent.sdacourseapplication.fortune;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.R.attr.animation;


@RequiresApi(api = VERSION_CODES.LOLLIPOP)
public class FortuneActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private Random random = new Random();
    private FrameLayout layer;
    private List<String> list = Arrays.asList("tralalala", "tralalal 2", "tralalal 3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);


        layer = (FrameLayout) findViewById(R.id.fortune_container);

        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.parent_layout);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        parentLayout.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCircularReveal((int) event.getX(), (int) event.getY(), layer);
                }

                return true;
            }
        });
    }

    private void animateCircularReveal(int x, int y, final FrameLayout layer) {

//        if (hidingAnimator != null && hidingAnimator.isRunning()) ||
//        (showingAnimator != null && showingAnimator.isRunning())){
//            return;
        }
        if (layer.getVisibility() == View.VISIBLE) {
            hidingAnimator = ViewAnimationUtils.createCircularReveal(layer, x , y, layer.getHeight())
                    hidingAnimator.addListener((AnimatorListenerAdapter) onAnimationEnd (animation)  {
                layer.setVisibility(View.INVISIBLE);
            }
            Animator circularReveal = ViewAnimationUtils
                    .createCircularReveal(layer, x,
                            y, layer.getHeight(), 0);
            circularReveal.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            circularReveal.start();
        } else {
            int color = Color.argb(255, random.nextInt(255), random.nextInt(100), random.nextInt(100));
            layer.setBackgroundColor(color);
            TextView text = (TextView) layer.findViewById(R.id.my_text);
            text.setText(list.get(random.nextInt(list.size())));
            Animator circularReveal = ViewAnimationUtils
                    .createCircularReveal(layer, x, y,
                            0, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            circularReveal.start();
        }
    }

    @Override
    public void hearShake() {

        animateCircularReveal(random.nextInt(layer.getWidth()),
                                random.nextInt(layer.getHeight()), layer);
    }
}
