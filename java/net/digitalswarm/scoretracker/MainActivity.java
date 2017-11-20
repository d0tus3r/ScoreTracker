package net.digitalswarm.scoretracker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * Global Variables
     * TODO: Maybe add a is2ndHalf boolean for later use in goal scored time calculation and display
     */
    int teamAScore = 0;
    int teamBScore = 0;
    long teamALastScore1;
    long teamALastScore2;
    long teamBLastScore1;
    long teamBLastScore2;
    int teamARedCards = 0;
    int teamAYellowCards = 0;
    int teamBRedCards = 0;
    int teamBYellowCards = 0;
    long timeRemaining = 2700000;
    boolean isPaused = true;
    boolean isRunning = false;
    public CountDownTimer cdTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamAScore(teamAScore);
        displayForTeamBScore(teamBScore);
        final TextView timerView = (TextView) findViewById(R.id.timer);
        /**
         * Timer Functionality
         * TODO: maybe clean this up so that there's no visible delay when resuming. // Maybe show milliseconds rounded to 100ths?
         * TODO: maybe onLongClick allow for custom time settings if not here, then dropdown options menu
         */
        timerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!isRunning) {
                    cdTimer = new CountDownTimer(timeRemaining, 1000) {
                        @Override
                        public void onTick(long millisTilFinished) {
                            if (isPaused) {
                                cancel();
                            } else {
                                timerView.setText(String.format("%02d:%02d", ((timeRemaining / 1000)/60), (timeRemaining / 1000)%60));
                                timeRemaining = millisTilFinished;
                            }
                        }

                        @Override
                        public void onFinish() {
                            timerView.setText("0:00");
                        }
                    }.start();
                    isPaused = false;
                    isRunning = true;
                } else {
                    isPaused = true;
                    isRunning = false;
                    cdTimer.cancel();
                }

            }
        });


        /**
         * create a way to interact with the textView teamAView(@id/team_a_score)
         * Creates 2 click listeners, adds a point to score when pressed quickly
         * removes a point from score when held
         * also handles the storing and displaying the last 2 scores
         * TODO: finish setting up onLongClick for score times and B team
         * TODO: Make last score time displayed as TotalTime(half+half) - timeRemaining
         */
        TextView teamAView = (findViewById(R.id.team_a_score));
        teamAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAScore++;
                displayForTeamAScore(teamAScore);
                if(teamAScore < 2){
                    teamALastScore1 = timeRemaining;
                    displayForTeamALast1(teamALastScore1);
                }
                if(teamAScore == 2){
                    teamALastScore2 = teamALastScore1;
                    teamALastScore1 = timeRemaining;
                    displayForTeamALast1(teamALastScore1);
                    displayForTeamALast2(teamALastScore2);
                }
                if(teamAScore > 2){
                    teamALastScore2 = teamALastScore1;
                    teamALastScore1 = timeRemaining;
                    displayForTeamALast1(teamALastScore1);
                    displayForTeamALast2(teamALastScore2);
                }
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
         * TODO: future goal: add the ability to enter player number after press to log player specific foul counts in addition to total team fouls
         * TODO: Future Goal: long press on Team Red&Yellow cards would bring up small card with player fouls
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
     * update display for last 2 scores team A + B
     */
    public void displayForTeamALast1(long lastScore){
        TextView teamALast1 = findViewById(R.id.team_a_goal_time_1);
        teamALast1.setText("+1       " + String.format("%02d:%02d", ((lastScore / 1000)/60), (lastScore / 1000)%60));
    }

    public void displayForTeamALast2(long lastScore){
        TextView teamALast2 = findViewById(R.id.team_a_goal_time_2);
        teamALast2.setText("+1       " + String.format("%02d:%02d", ((lastScore / 1000)/60), (lastScore / 1000)%60));
    }

    public void displayForTeamBLast1(int lastScore){
        TextView teamBLast1 = findViewById(R.id.team_b_goal_time_1);
        teamBLast1.setText("+1       " + String.format("%02d:%02d", ((lastScore / 1000)/60), (lastScore / 1000)%60));
    }

    public void displayForTeamBLast2(int lastScore){
        TextView teamBLast2 = findViewById(R.id.team_b_goal_time_2);
        teamBLast2.setText("+1       " + String.format("%02d:%02d", ((lastScore / 1000)/60), (lastScore / 1000)%60));
    }

    /**
     * Resets the scores && time
     * TODO: Move this to a drop down option menu / no need for screen space used
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
    /**
     * TODO: Future Goal / banner ad on bottom
     */






}
