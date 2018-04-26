package com.derk.damagebreakdown.model;

public class Match {
    private String matchID;

    Match(String matchID){
        this.matchID = matchID;
    }

    public String getMatchID() {
        return matchID;
    }

    @Override
    public String toString() {
        return "Match "  + matchID;
    }
}
