package com.example.android.dicegame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    int user_overall_score = 0;
    int user_turn_score = 0;
    int computer_overall_score = 0;
    int computer_turn_score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int generate_die_value() {
        int min = 1, max = 6, dice_value;
        Random rand = new Random();
        dice_value = rand.nextInt(max - min + 1) + min;
        ImageView dice = (ImageView) findViewById(R.id.dice);
        switch (dice_value) {
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dice.setImageResource(R.drawable.dice6);
        }
        return dice_value;
    }

    public void roll(View view) {
        int dv = generate_die_value();
        TextView text_update = (TextView) findViewById(R.id.label);
        if (dv == 1) {
            user_turn_score = 0;
            text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score);
            text_update.setTextSize(15);
            computerTurn();
        } else {
            user_turn_score = user_turn_score + dv;
            text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score + " your turn score:" + user_turn_score);
            text_update.setTextSize(12);
        }
    }

    public void reset(View view) {
        user_turn_score = user_overall_score = computer_overall_score = computer_turn_score = 0;
        TextView text_update = (TextView) findViewById(R.id.label);
        text_update.setText("your score:0 computer score:0");
        text_update.setTextSize(15);
        Button roll_button = (Button) findViewById(R.id.rb);
        Button hold_button = (Button) findViewById(R.id.hb);
        roll_button.setEnabled(true);
        hold_button.setEnabled(true);
    }

    public void hold(View view) {
        user_overall_score = user_overall_score + user_turn_score;
        user_turn_score = 0;
        TextView text_update = (TextView) findViewById(R.id.label);
        text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score);
        text_update.setTextSize(15);
        int cw=winner_game();
        if(cw==1)
            return;
        computerTurn();

    }

    public void computerTurn() {
        Handler timehandler = new Handler();
        final Button roll_button = (Button) findViewById(R.id.rb);
        final Button hold_button = (Button) findViewById(R.id.hb);
        final TextView text_update = (TextView) findViewById(R.id.label);
        roll_button.setEnabled(false);
        hold_button.setEnabled(false);
        while (true) {
            int dv = generate_die_value();
            if (dv == 1) {
                computer_turn_score = 0;
                text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score);
                text_update.setTextSize(15);
                roll_button.setEnabled(true);
                hold_button.setEnabled(true);
                break;
            } else {
                computer_turn_score = computer_turn_score + dv;
                if (computer_turn_score >= 15) {
                    computer_overall_score = computer_overall_score + computer_turn_score;
                    computer_turn_score = 0;
                    text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score);
                    text_update.setTextSize(15);
                    roll_button.setEnabled(true);
                    hold_button.setEnabled(true);
                    winner_game();
                    break ;
                } else {
                    text_update.setText("your score:" + user_overall_score + " computer score:" + computer_overall_score + " computer turn score:" + computer_turn_score);
                    text_update.setTextSize(12);
                }
            }
        }
    }
    public int winner_game()
    {
        Button roll_button = (Button) findViewById(R.id.rb);
        Button hold_button = (Button) findViewById(R.id.hb);
        if(user_overall_score>=100) {
            Toast.makeText(this, "Game Over.Winner is the user", Toast.LENGTH_LONG).show();
            roll_button.setEnabled(false);
            hold_button.setEnabled(false);
            return 1;
        }
        else if(computer_overall_score>=100) {
            Toast.makeText(this, "Game over.Winner is the computer", Toast.LENGTH_LONG).show();
            roll_button.setEnabled(false);
            hold_button.setEnabled(false);
        }
        return 0;
    }
}




