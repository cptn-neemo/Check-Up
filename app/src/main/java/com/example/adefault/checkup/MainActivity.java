package com.example.adefault.checkup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.AddGame;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ProgressBar progressBar;
    final DatabaseReference mDataBaseReference = FirebaseDatabase.getInstance().getReference();

    ArrayList<Game> gameList;
    private final String ARRAY_LIST_KEY = "array list";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_activity_progress_bar);
        gameList = new ArrayList<>();

        final RecyclerView rGameRecyclerView = (RecyclerView) findViewById(R.id.tv_games);
        //final GameAdapter gameAdapter = new GameAdapter(this, gameList);
        //final GameCardAdapter gameCardAdapter = new GameCardAdapter(this, gameList);

        //rGameRecyclerView.setAdapter(gameAdapter);
        //rGameRecyclerView.setAdapter(gameCardAdapter);
        //rGameRecyclerView.setLayoutManager(new LinearLayoutManager(this));





        mDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (gameList != null) {
                    gameList.clear();
                }

                Log.d(ARRAY_LIST_KEY,"Game list is clear");
                progressBar.setVisibility(View.VISIBLE);

                for (DataSnapshot gameSnapchat : dataSnapshot.getChildren()) {
                    Game recievedGame = gameSnapchat.getValue(Game.class);
                    gameList.add(recievedGame);
                    Log.d("New game: ","A new game was read");
                }
                final GameCardAdapter gameCardAdapter = new GameCardAdapter(getApplicationContext(), gameList);
                rGameRecyclerView.setAdapter(gameCardAdapter);
                //gameAdapter.notifyDataSetChanged();
                gameCardAdapter.notifyDataSetChanged();
                rGameRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_add) {
            Intent startAddGameActivity = new Intent(getApplicationContext(), AddGame.class);
            startAddGameActivity.putExtra(ARRAY_LIST_KEY, gameList);
            startActivity(startAddGameActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Game> createListOfGames(int lengthOfList) {
        ArrayList<Game> newGameList = new ArrayList<>();

        for (int i = 0; i < lengthOfList; i++){
            Game game = new Game();
            game.setGameName("Game " + i);
            game.setOwnerOfGame("Max" + i);
            game.setNumPlayers(i);
            game.setLocationOfGame("Location " + i);

            newGameList.add(game);

        }

        return newGameList;
    }

    public void loadInData() {
        mDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                gameList.clear();
                Log.d(ARRAY_LIST_KEY,"Game list is clear");

                for (DataSnapshot gameSnapchat : dataSnapshot.getChildren()) {
                    Game recievedGame = gameSnapchat.getValue(Game.class);
                    gameList.add(recievedGame);
                    Log.d("New game: ","A new game was read");
                }

                gameList = createListOfGames(5);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public ArrayList<Game> getGameList() {

        return gameList;
    }

    public static String convertGameTime(String oldGameTime) {
        String hour = oldGameTime.substring(0,oldGameTime.indexOf(':'));
        int intHour = Integer.parseInt(hour);
        if (intHour > 12) {
            intHour -= 12;
        }

        String time = intHour + ":" + oldGameTime.substring(oldGameTime.indexOf(':') + 1);
        return time;
    }

}
