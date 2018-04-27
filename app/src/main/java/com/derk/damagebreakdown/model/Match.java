package com.derk.damagebreakdown.model;

public class Match {
    private String matchID;
    private String gameMode;
    private String date;
    private String time;

    Match(String matchID){
        this.matchID = matchID;
    }

    public String getMatchID() {
        return matchID;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    // Format YYYY-MM-DDThh:mm:ssZ
    public void setCreatedAt(String createdAt) {
        formatDate(createdAt);
    }

    private void formatDate(String createdAt){
        String[] dateTime = createdAt.split("T");
        date = dateTime[0];
        time = dateTime[1].split("Z")[0];
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Match "  + matchID;
    }
}
