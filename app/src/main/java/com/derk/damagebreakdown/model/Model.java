package com.derk.damagebreakdown.model;

import com.derk.damagebreakdown.controller.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Model {
    private static String TAG = "derkberklin";

    public void retrieveRecentMatchIDs(final Callback<List<String>> callback){
        Callback<JSONObject> onUserDataResult = new Callback<JSONObject>() {
            @Override
            public void onResult(JSONObject result) {
                try {
                    JSONArray matchesArray = JSONHelper.getMatches(result);
                    callback.onResult(JSONHelper.getMatchIDs(matchesArray));
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            }
        };

        API.getUserData(TAG, onUserDataResult);
    }

    public void getMatchById(String id, final Callback<Match> callback){
        API.lookupMatch(id, new Callback<JSONObject>() {
            @Override
            public void onResult(JSONObject result) {
                try {
                    callback.onResult(JSONHelper.createMatch(result));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
