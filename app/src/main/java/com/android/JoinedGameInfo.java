package com.android;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adefault.checkup.Game;
import com.example.adefault.checkup.R;

public class JoinedGameInfo extends AppCompatActivity {

    private TextView mGameOwnerTextView;
    private TextView mGameNameTextView;
    private TextView mNumPlayersTextView;
    private TextView mGameLocation;
    private TextView mTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_game_info);

        mGameOwnerTextView = (TextView) findViewById(R.id.JOINEDGAME_game_owner);
        mGameLocation = (TextView) findViewById(R.id.JOINEDGAME_game_location);
        mGameNameTextView = (TextView) findViewById(R.id.JOINEDGAME_gameName_textview);
        mNumPlayersTextView = (TextView) findViewById(R.id.JOINEDGAME_num_players);
        mTime = (TextView) findViewById(R.id.JOINEDGAME_time);

        final Game recievedGame = (Game) getIntent().getExtras().getSerializable("Sent game");
        int newNumPlayers = recievedGame.getNumPlayers() + 1;

        mGameOwnerTextView.setText("The owner is " + recievedGame.getOwnerOfGame() + ".");
        mGameLocation.setText("This game will take place at " + recievedGame.getLocationOfGame() + ".");
        mGameNameTextView.setText("You just joined " + recievedGame.getGameName());
        mNumPlayersTextView.setText("Number of players: " + newNumPlayers);
        mTime.setText("The start time is " + recievedGame.getGameTime());


    }

}
