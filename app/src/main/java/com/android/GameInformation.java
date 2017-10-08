package com.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adefault.checkup.Game;
import com.example.adefault.checkup.MainActivity;
import com.example.adefault.checkup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class GameInformation extends AppCompatActivity {

    private TextView mGameOwnerTextView;
    private TextView mGameNameTextView;
    private TextView mNumPlayersTextView;
    private TextView mGameTime;
    private TextView mGameLocation;
    private Button mJoinButton;

    private FirebaseDatabase mFireBaseDat = FirebaseDatabase.getInstance();
    private DatabaseReference mFireGameRef = mFireBaseDat.getReference();

    private void loadUpScreen(Game recievedGame) {
        setContentView(R.layout.activity_game_information);

        mGameNameTextView = (TextView) findViewById(R.id.GAMEINFO_game_name_textView);
        mGameOwnerTextView = (TextView) findViewById(R.id.GAMEINFO_game_owner);
        mGameTime = (TextView) findViewById(R.id.GAMEINFO_game_time);
        mGameLocation = (TextView) findViewById(R.id.GAMEINFO_game_location);
        mNumPlayersTextView = (TextView) findViewById(R.id.GAMEINFO_number_of_players);
        mJoinButton = (Button) findViewById(R.id.GAMEINFO_join_button);

        mGameOwnerTextView.setText("Owner: " + recievedGame.getOwnerOfGame());
        mGameNameTextView.setText(recievedGame.getGameName());
        mNumPlayersTextView.setText("Number of players: "+recievedGame.getNumPlayers() + "");
        mGameTime.setText("Start Time: " + recievedGame.getGameTime());
        mGameLocation.setText("Location: " + recievedGame.getLocationOfGame());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);

        mGameNameTextView = (TextView) findViewById(R.id.GAMEINFO_game_name_textView);
        mGameOwnerTextView = (TextView) findViewById(R.id.GAMEINFO_game_owner);
        mGameTime = (TextView) findViewById(R.id.GAMEINFO_game_time);
        mGameLocation = (TextView) findViewById(R.id.GAMEINFO_game_location);
        mNumPlayersTextView = (TextView) findViewById(R.id.GAMEINFO_number_of_players);
        mJoinButton = (Button) findViewById(R.id.GAMEINFO_join_button);

        Log.d("END: ", "All textviews found.");

        Intent recievedIntent = getIntent();
        final Game recievedGame = (Game) recievedIntent.getExtras().getSerializable(Intent.EXTRA_COMPONENT_NAME);

        mGameOwnerTextView.setText("Owner: " + recievedGame.getOwnerOfGame());
        mGameNameTextView.setText(recievedGame.getGameName());
        mNumPlayersTextView.setText("Number of players: "+recievedGame.getNumPlayers() + "");
        mGameTime.setText("Start Time: " + recievedGame.getGameTime());
        mGameLocation.setText("Location: " + recievedGame.getLocationOfGame());

        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add firebase support
                if (recievedGame.getGameAvailable() == true)
                    Log.d("recieved game avail: ", "true" );
                else
                    Log.d("rGameAvail: ", "false");

                if (recievedGame.getNumPlayers() >= 10) {
                    recievedGame.setGameAvailable(false);
                    mFireGameRef.child(recievedGame.getGameID()).child("gameAvailable").setValue(false);
                }

                if (recievedGame.getGameAvailable() == true) {

                    mFireGameRef.child(recievedGame.getGameID()).child("numPlayers").setValue(recievedGame.getNumPlayers() + 1);
                    int newNumPlayers = recievedGame.getNumPlayers() + 1;



                    mNumPlayersTextView.setText("Number of players: "+recievedGame.getNumPlayers() + "");

                    Intent intentToJoinedGameInfo = new Intent(getApplicationContext(), JoinedGameInfo.class);
                    intentToJoinedGameInfo.putExtra("Sent game", recievedGame);
                    startActivity(intentToJoinedGameInfo);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Sorry, this game is now full.", Toast.LENGTH_LONG).show();
                    mFireGameRef.child(recievedGame.getGameID()).child("gameAvailable").setValue(new Boolean(false));
                    mFireGameRef.child(recievedGame.getGameID()).removeValue();

                    Intent backToHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(backToHome);
                }

            }
        });

    }
}
