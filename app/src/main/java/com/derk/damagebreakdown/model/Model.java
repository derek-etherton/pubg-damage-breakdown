package com.derk.damagebreakdown.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Model {
    // TODO: TAG is user-provided
    private static String TAG = "derkberklin";

    public void refresh(){
        API.getUserData(TAG, new APICallback() {
            @Override
            public void onResult(JSONObject result) {
                try {
                    JSONArray matches = JSONHelper.getMatches(result);
                } catch (JSONException je) {
                    je.printStackTrace();
                }
                // TODO: Populate list w/ matches
                // Write JSONArray adapter for match list
                // On match click : getMatchID
                // lookUpMatch(matchID) to get telemetry data
            }
        });
    }
}
