package com.example.rent.sdacourseapplication.game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> images = Arrays.asList(R.drawable.one, R.drawable.two, R.drawable.three,
            R.drawable.four, R.drawable.five);

    private Random random = new Random();
    private ImageView firstImageView;
    private ImageView secondImageView;
    private Button firstPlayerButton;
    private Button secondPlayerButton;
    private CountDownTimer countDownTimer;
    private Button startButton;
    private boolean isRunning = false;
    private IntroAnimator introAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_main);

        firstImageView = (ImageView) findViewById(R.id.first_image_view);
        secondImageView = (ImageView) findViewById(R.id.second_image_view);
        startButton = (Button) findViewById(R.id.start_button);
        final TextView introTextView = (TextView) findViewById(R.id.introTextView);
        introAnimator = new IntroAnimator(introTextView);

        firstPlayerButton = (Button) findViewById(R.id.firstPlayerButton);
        secondPlayerButton = (Button) findViewById(R.id.secondPlayerButton);
        firstPlayerButton.setOnClickListener(this);
        secondPlayerButton.setOnClickListener(this);


        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int firstImage = images.get(random.nextInt(images.size()));
                int secondImage = images.get(random.nextInt(images.size()));
                firstImageView.setImageResource(firstImage);
                firstImageView.setTag(firstImage);
                secondImageView.setImageResource(secondImage);
                secondImageView.setTag(secondImage);
            }

            @Override
            public void onFinish() {
                startButton.setVisibility(View.VISIBLE);

            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                animateCounter(introTextView, new Runnable() {
                    @Override
                    public void run() {
                        isRunning = true;
                        firstImageView.setVisibility(View.VISIBLE);
                        secondImageView.setVisibility(View.VISIBLE);
                        firstPlayerButton.setBackgroundColor(ContextCompat.getColor(GameActivity.this, android.R.color.holo_blue_light));
                        secondPlayerButton.setBackgroundColor(ContextCompat.getColor(GameActivity.this, android.R.color.holo_red_dark));
                        countDownTimer.start();
                    }
                }, 3);


                startButton.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (!isRunning) {
            return;
        }
        if (firstImageView.getTag().equals(secondImageView.getTag())) {
            if (view.getId() == firstPlayerButton.getId()) {
                firstPlayerButton.setBackgroundColor(Color.GREEN);
                secondPlayerButton.setBackgroundColor(Color.RED);
                Toast.makeText(this, "PLAYER 1 WINS", Toast.LENGTH_LONG).show();
            } else {
                firstPlayerButton.setBackgroundColor(Color.RED);
                secondPlayerButton.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "PLAYER 2 WINS", Toast.LENGTH_LONG).show();
            }


        } else {
            if (view.getId() == firstPlayerButton.getId()) {
                firstPlayerButton.setBackgroundColor(Color.RED);
                secondPlayerButton.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "PLAYER 2 WINS", Toast.LENGTH_LONG).show();

            } else {
                firstPlayerButton.setBackgroundColor(Color.GREEN);
                secondPlayerButton.setBackgroundColor(Color.RED);
                Toast.makeText(this, "PLAYER 1 WINS", Toast.LENGTH_LONG).show();

            }
        }

        firstImageView.setVisibility(View.GONE);
        secondImageView.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);

        countDownTimer.cancel();
        isRunning = false;

    }


    private void animateCounter(final TextView introTextView, final Runnable animationsEnd, final int counter) {

        String text = counter == 0 ? "START" : String.valueOf(counter);

        introTextView.setText(text);
        introTextView.setAlpha(1);
        introTextView.setScaleX(1);
        introTextView.setScaleY(1);
        introTextView.animate()
                .alpha(0)
                .scaleX(3)
                .scaleY(3)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {

                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     if (counter > 0) {
                                         animateCounter(introTextView, animationsEnd, counter - 1);
                                     } else {
                                         animationsEnd.run();
                                     }
                                 }
                             }
                )

                .start();
    }


}
