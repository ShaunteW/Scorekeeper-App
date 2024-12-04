package org.forestpark.scorekeeperwilson2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView scoreText2;
    private Button increaseButton, decreaseButton;
    private Button increaseButton2, decreaseButton2;
    private EditText team1EditName, team2EditName;
    private int score;
    private int score2;

    private Button resetBtn;

    MediaPlayer mysound;

    CountDownTimer timer;

    long timeLeftInMillis = 60000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize score variables
        score = 0;
        score2 = 0;

        // Initialize views
        scoreText = findViewById(R.id.scoreText);
        scoreText2 = findViewById(R.id.scoreText2);
        increaseButton = findViewById(R.id.increaseButton1);
        decreaseButton = findViewById(R.id.decreaseButton1);
        increaseButton2 = findViewById(R.id.increaseButton2);
        decreaseButton2 = findViewById(R.id.decreaseButton2);
        team1EditName = findViewById(R.id.Team1EditName);
        team2EditName = findViewById(R.id.Team2EditName);
        resetBtn = findViewById(R.id.ResetBtn);
        TextView timerText = findViewById(R.id.timer_text);
        Button startTimerButton = findViewById(R.id.start_timer);
        Button resetTimerButton = findViewById(R.id.reset_timer);
        startTimerButton.setOnClickListener(v -> startTimer(timerText));
        resetTimerButton.setOnClickListener(v -> resetTimer(timerText));



        // Increase score1
        increaseButton.setOnClickListener(v -> {
            score++;
            updateScore();
            mysound.start();// Update scoreText
        });


        // Increase score2
        increaseButton2.setOnClickListener(v -> {
            score2++;
            updateScore2();
            mysound.start();// Update scoreText2
        });

        // Decrease score1
        decreaseButton.setOnClickListener(v -> {
            if (score > 0) score--;
            updateScore();
            mysound.start();// Update scoreText
        });

        // Decrease score2
        decreaseButton2.setOnClickListener(v -> {
            if (score2 > 0) score2--;
            updateScore2();
            mysound.start();// Update scoreText2
        });

        resetBtn.setOnClickListener(v -> {
            if (score> 0) score = 0;
            updateScore();
            if (score2 > 0) score2 = 0;
            updateScore();
            updateScore2();
            mysound.start();
        });

        mysound=MediaPlayer.create(MainActivity.this,R.raw.audio);

    }

    public void playit(View v){
        mysound.start();
    }


    // Method to update scoreText (for score1)
    private void updateScore() {
        scoreText.setText(String.valueOf(score));
    }

    // Method to update scoreText2 (for score2)
    private void updateScore2() {
        scoreText2.setText(String.valueOf(score2));
    }

    private void startTimer(TextView timerText) {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText(timerText);
            }


            @Override
            public void onFinish() {
                timerText.setText("00:00");
                playChimeSound();


            }
        }.start();
    }


    private void resetTimer(TextView timerText) {
        if (timer != null) {
            timer.cancel();
        }
        timeLeftInMillis = 60000; // Reset to 10 minutes
        updateTimerText(timerText);
    }


    private void updateTimerText(TextView timerText) {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerText.setText(formattedTime);
    }
    private void playChimeSound() {
        if (mysound != null) {
            mysound.start();
        }
    }



}

