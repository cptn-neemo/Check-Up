package com.example.adefault.checkup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.GameInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by default on 9/6/2017.
 */

public class GameCardAdapter extends RecyclerView.Adapter<GameCardAdapter.GameViewHolder> {

    private ArrayList<Game> gameDataList;
    private Context mGameDataContext;
    private FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mGameRef = mFireBaseDatabase.getReference();

    public GameCardAdapter(Context context, ArrayList<Game> gList) {

        mGameDataContext = context;
        gameDataList = gList;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View dataView = layoutInflater.inflate(R.layout.game_layout_card,parent,false);
        return new GameViewHolder(dataView);
    }



    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {

        ImageView imgView = holder.mGameDataImageView;
        Button gameButton = holder.mGameDataButton;
        TextView infoText = holder.mGameInfoText;

        if (gameDataList == null) {

            infoText.setText("Empty");
            Button button = holder.mGameDataButton;
            button.setText("Empty");
            return;
        }
        Game game = gameDataList.get(position);

        if (game.getNumPlayers() >= 10) {
            mGameRef.child(game.getGameID()).removeValue();
        }
        else {
            infoText.setText(game.getGameName() + "\n" + "Number of Players: " + game.getNumPlayers() + "\n"
                    + "Time: " + game.getGameTime());
            imgView.setImageResource(R.drawable.sellery);
            gameButton.setText("Join Game");
        }



    }

    @Override
    public int getItemCount() {

        if (gameDataList == null)
            return 0;
        return gameDataList.size();

    }

    public String getFullGameData(Game game) {

        String fullGameData = "Name: " + game.getGameName() + "\n"
                + "Location: " + game.getLocationOfGame() + "\n"
                + "NumPlayers: " + game.getNumPlayers() + "\n"
                + "Owner: " + game.getOwnerOfGame() + "\n";

        return fullGameData;
    }

    private Context getmGameDataContext() {
        return mGameDataContext;
    }


    public class GameViewHolder extends RecyclerView.ViewHolder {

        public ImageView mGameDataImageView;
        public Button mGameDataButton;
        public TextView mGameInfoText;

        public GameViewHolder(final View currentView) {

            super(currentView);

            mGameDataButton = currentView.findViewById(R.id.card_button_more_info);
            mGameDataImageView = currentView.findViewById(R.id.card_image_view);
            mGameInfoText = currentView.findViewById(R.id.card_text_information);

            mGameDataButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        //TODO: Add intent to new activitiy
                        int position = getAdapterPosition();
                        Game game = gameDataList.get(position);
                        Intent goToAddGame = new Intent(getmGameDataContext(), GameInformation.class);
                        goToAddGame.putExtra(Intent.EXTRA_COMPONENT_NAME, game);

                        currentView.getContext().startActivity(goToAddGame);


                        Toast.makeText(getmGameDataContext(),"This works " + game.getGameName(), Toast.LENGTH_LONG).show();
                    }


            });
        }

    }

}
