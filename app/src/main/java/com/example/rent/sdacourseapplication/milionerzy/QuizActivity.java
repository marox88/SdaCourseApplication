package com.example.rent.sdacourseapplication.milionerzy;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.rent.sdacourseapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.R.attr.start;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milionerzy);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_Bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(10000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });


        objectAnimator.start();

        String json = null;
        try {
            loadQuizJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String loadQuizJson() throws IOException {

        StringBuilder buf = new StringBuilder();
        InputStream json = getAssets().open("quiz_data.json");
        BufferedReader in =
                new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();

        return buf.toString();

    }
}
