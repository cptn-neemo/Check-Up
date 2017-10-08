package com.example.adefault.checkup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by default on 9/6/2017.
 */

public class Game implements Serializable{

    private int numPlayers;
    private String locationOfGame;
    private String ownerOfGame;
    private String gameName;
    private String gameID;
    private String gameTime;
    private boolean gameAvailable;

    public Game(String gName, String owner, String location, String gameTime){
        this.numPlayers = 1;
        this.locationOfGame = location;
        this.ownerOfGame = owner;
        this.gameName = gName;
        this.gameTime = gameTime;
        this.gameAvailable = true;
    }

    public Game () {}




    public String getGameName() {
        return gameName;
    }

    public void setGameName(String newGameName) {
        gameName = newGameName;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int num) {
        numPlayers = num;
    }

    public String getLocationOfGame() {
        return locationOfGame;
    }

    public void setLocationOfGame(String location) {
        locationOfGame = location;
    }

    public String getOwnerOfGame() {
        return ownerOfGame;
    }

    public void setOwnerOfGame(String newOwnerOfGame) {
        ownerOfGame = newOwnerOfGame;
    }

    public String getGameID() {return gameID;};

    public void setGameID(String newGameId) {gameID = newGameId;};

    public String getGameTime() {return gameTime;}
    public void setGameTime(String newGameTime) {gameTime = newGameTime;}

    public boolean getGameAvailable() {return gameAvailable;}
    public void setGameAvailable(boolean b) {gameAvailable = b;}

}
