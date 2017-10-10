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
        TextView infoText = holder.mGameInfoText;

        int imageNumber;

        if (gameDataList == null) {

            infoText.setText("Empty");
            return;
        }
        Game game = gameDataList.get(position);

        switch (game.getLocationOfGame()) {
            case "Sellery":
                imageNumber = 0;
                break;
            case "SERF":
                imageNumber = 1;
                break;
            case "Natatorium":
                imageNumber = 2;
                break;
            case "Shell":
                imageNumber = 3;
                break;
            default:
                imageNumber = -1;
                break;
        }

        String gameTime = game.getGameTime();
        gameTime = MainActivity.convertGameTime(gameTime);

        if (game.getNumPlayers() >= 10) {
            mGameRef.child(game.getGameID()).removeValue();
        }
        else {
            infoText.setText(game.getGameName() + "\n" + "Number of Players: " + game.getNumPlayers() + "\n"
                    + "Time: " + gameTime);
            //imgView.setImageResource(R.drawable.sellery);

            switch (imageNumber) {
                case 0:
                    imgView.setImageResource(R.drawable.sellery);
                    break;
                case 1:
                    imgView.setImageResource(R.drawable.serf);
                    break;
                case 2:
                    imgView.setImageResource(R.drawable.nat);
                    break;
                case 3:
                    imgView.setImageResource(R.drawable.shell);
                    break;
                default:
                    imgView.setImageResource(R.drawable.shell);
            }
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
        public TextView mGameInfoText;


        public GameViewHolder(final View currentView) {

            super(currentView);
            mGameDataImageView = currentView.findViewById(R.id.card_image_view);
            mGameInfoText = currentView.findViewById(R.id.card_text_information);


            currentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
