package com.derk.damagebreakdown.controller;
import com.derk.damagebreakdown.model.Match;
import com.derk.damagebreakdown.model.Model;

import java.util.List;

public class Controller {
    private Model model;

    public Controller(){
        model = new Model();
    }

    public void lookUpMatchById(String id, Callback<Match> callback){
        model.getMatchById(id, callback);
    }

    public void refreshList(Callback<List<String>> callback) {
        model.retrieveRecentMatchIDs(callback);
    }


}
