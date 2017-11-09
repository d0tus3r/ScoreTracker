package net.digitalswarm.scoretracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Global Variables for Team Score
     */
    int teamAScore = 0;
    int teamBScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);



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
                displayForTeamA(teamAScore);
            }
        });
        teamAView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamAScore--;
                displayForTeamA(teamAScore);
                return true;
            }
        });
        TextView teamBView = (findViewById(R.id.team_b_score));
        teamBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBScore++;
                displayForTeamB(teamBScore);
            }
        });
        teamBView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teamBScore--;
                displayForTeamB(teamBScore);
                return true;
            }
        });


    }




    /**
     * Displays the given score for Team A and B.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }


    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void goalScoredA(View view){
        teamAScore++;
        displayForTeamA(teamAScore);
    }

    public void goalScoredB(View view){
        teamBScore++;
        displayForTeamB(teamBScore);
    }
    /**
     * Resets the scores && time
     */
    public void resetScore(View view){
        teamAScore = 0;
        teamBScore = 0;
        displayForTeamA(teamAScore);
        displayForTeamB(teamBScore);
    }






}
