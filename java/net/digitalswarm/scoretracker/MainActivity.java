package net.digitalswarm.scoretracker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    /**
     * Global Variables for Team Score
     */
    int teamAScore = 0;
    int teamBScore = 0;
    int teamARedCards = 0;
    int teamAYellowCards = 0;
    int teamBRedCards = 0;
    int teamBYellowCards = 0;
    int countTimer = 2700;
    boolean pauseTimer = false;
    public TextView timerView = (TextView) findViewById(R.id.timer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamAScore(teamAScore);
        displayForTeamBScore(teamBScore);
        

        timer();

        /**
         * create a way to interact with the textView teamAView(@id/team_a_score)
         * Creates 2 click listeners, adds a point to score when pressed quickly
         * removes a point from score when held
         */
        TextView teamAView = (findViewById(R.id.team_a_score));
        teamAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAScore++;
                displayForTeamAScore(teamAScore);
            }
        });
        teamAView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamAScore--;
                displayForTeamAScore(teamAScore);
                return true;
            }
        });
        TextView teamBView = (findViewById(R.id.team_b_score));
        teamBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBScore++;
                displayForTeamBScore(teamBScore);
            }
        });
        teamBView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamBScore--;
                displayForTeamBScore(teamBScore);
                return true;
            }
        });

        /**
         * on click listeners for the fouls
         * quick press = ++ / long press = --
         * future goal: add the ability to enter player number after press to log 
         * player specific foul counts in addition to total team fouls
         */
        TextView teamARedCard = (findViewById(R.id.team_a_red));
        teamARedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamARedCards++;
                displayForTeamARed(teamARedCards);
            }
        });
        teamARedCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamARedCards--;
                displayForTeamARed(teamARedCards);
                return true;
            }
        });

        TextView teamAYellowCard = (findViewById(R.id.team_a_yellow));
        teamAYellowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAYellowCards++;
                displayForTeamAYellow(teamAYellowCards);
            }
        });
        teamAYellowCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamAYellowCards--;
                displayForTeamAYellow(teamAYellowCards);
                return true;
            }
        });

        TextView teamBRedCard = (findViewById(R.id.team_b_red));
        teamBRedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBRedCards++;
                displayForTeamBRed(teamBRedCards);
            }
        });
        teamBRedCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamBRedCards--;
                displayForTeamBRed(teamBRedCards);
                return true;
            }
        });

        TextView teamBYellowCard = (findViewById(R.id.team_b_yellow));
        teamBYellowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBYellowCards++;
                displayForTeamBYellow(teamBYellowCards);
            }
        });
        teamBYellowCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamBYellowCards--;
                displayForTeamBYellow(teamBYellowCards);
                return true;
            }
        });



    }

    public void timer(){
        Timer timer = new Timer();
        timerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pauseTimer){
                    pauseTimer = false;
                }
                if(!pauseTimer){
                    pauseTimer = true;

                }
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String s_time = String.format("%02d:%02d", (countTimer % 3600) / 60, countTimer % 60);
                        timerView.setText(s_time);
                        if (!pauseTimer) countTimer--;
                    }
                });
            }
        }, 1000, 1000);
    }
/**
    public void displayForTimer(String time){
        TextView timerView = findViewById(R.id.timer);
        timerView.setText(time);
    }
*/

    /**
     * Displays the given score for Team A and B.
     */
    public void displayForTeamAScore(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamBScore(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Update display for TeamA red & yellow cards
     * @param redA
     */
    public void displayForTeamARed(int redA){
        TextView teamARedCard = findViewById(R.id.team_a_red);
        teamARedCard.setText(String.valueOf(redA));
    }

    public void displayForTeamAYellow(int yellowA){
        TextView teamAYellowCard = findViewById(R.id.team_a_yellow);
        teamAYellowCard.setText(String.valueOf(yellowA));
    }

    public void displayForTeamBRed(int redB){
        TextView teamBRedCard = findViewById(R.id.team_b_red);
        teamBRedCard.setText(String.valueOf(redB));
    }

    public void displayForTeamBYellow(int yellowB){
        TextView teamBYellowCard = findViewById(R.id.team_b_yellow);
        teamBYellowCard.setText(String.valueOf(yellowB));
    }

    /**
     * Resets the scores && time
     */
    public void resetScore(View view){
        teamAScore = 0;
        teamBScore = 0;
        teamARedCards = 0;
        teamAYellowCards = 0;
        teamBRedCards = 0;
        teamBYellowCards = 0;
        displayForTeamAScore(teamAScore);
        displayForTeamBScore(teamBScore);
        displayForTeamARed(teamARedCards);
        displayForTeamAYellow(teamAYellowCards);
        displayForTeamBRed(teamBRedCards);
        displayForTeamBYellow(teamBYellowCards);
    }






}
