package com.derk.damagebreakdown.controller;
import com.derk.damagebreakdown.model.Model;

public class Controller {
    private Model model;

    public Controller(){
        model = new Model();
    }

    public void refresh() {
        model.refresh();
    }
}
