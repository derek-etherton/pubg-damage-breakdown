package com.derk.damagebreakdown.controller;
import com.derk.damagebreakdown.model.Match;
import com.derk.damagebreakdown.model.Model;

import java.util.List;

public class Controller {
    private Model model;

    public Controller(){
        model = new Model();
    }

    public void refreshList(Callback<List<Match>> callback) {
        model.retrieveUserMatchList(callback);
    }
}
