package com.derk.damagebreakdown.model;

import com.derk.damagebreakdown.controller.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Model {
    // TODO: TAG is user-provided
    private static String TAG = "derkberklin";

    public void retrieveUserMatchList(final Callback<List<Match>> callback){
        Callback<JSONObject> onUserDataResult = new Callback<JSONObject>() {
            @Override
            public void onResult(JSONObject result) {
                try {
                    JSONArray matchesArray = JSONHelper.getMatches(result);
                    List<Match> matches = JSONHelper.createMatchList(matchesArray);
                    callback.onResult(matches);
                } catch (JSONException je) {
                    je.printStackTrace();
                }

                // Write JSONArray adapter for match list
                // On match click : lookUpMatch(matchID) to get telemetry data
            }
        };

        API.getUserData(TAG, onUserDataResult);
    }
}
