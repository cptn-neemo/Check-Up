package com.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adefault.checkup.Game;
import com.example.adefault.checkup.MainActivity;
import com.example.adefault.checkup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddGame extends AppCompatActivity {

    private EditText mLocationEditText;
    private EditText mOwnerEditText;
    private EditText mGameNameEditText;
    private EditText mGameTime;
    private Spinner mLocationSpinner;
    private Button mSubmitGameButton;
    ArrayList<Game> gameList;

    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        mLocationEditText = (EditText) findViewById(R.id.edit_text_location);
        mOwnerEditText = (EditText) findViewById(R.id.edit_text_owner);
        mGameNameEditText = (EditText) findViewById(R.id.edit_text_gameName);
        mSubmitGameButton = (Button) findViewById(R.id.submit_game_button);
        mGameTime = (EditText) findViewById(R.id.ADDGAME_time);
        mLocationSpinner = (Spinner) findViewById(R.id.AG_game_location_spinner);


        mSubmitGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            addGame();
            }
        });

    }

    private void addGame() {
        String locationText = mLocationEditText.getText().toString();
        String ownerText = mOwnerEditText.getText().toString();
        String gameName = mGameNameEditText.getText().toString();
        String gameTime = mGameTime.getText().toString();

        if (!TextUtils.isEmpty(locationText) || !TextUtils.isEmpty(ownerText) || !TextUtils.isEmpty(gameName)) {
            String ID = mRootRef.push().getKey();

            Game game = new Game(gameName, ownerText, locationText, gameTime);
            game.setGameID(ID);

            mRootRef.child(ID).setValue(game);

            Toast.makeText(this, "Game added!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Please enter all of the fields.", Toast.LENGTH_LONG).show();
        }

    }








}
