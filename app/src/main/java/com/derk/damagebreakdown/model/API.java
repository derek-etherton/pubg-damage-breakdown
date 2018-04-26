package com.derk.damagebreakdown.model;

import com.derk.damagebreakdown.controller.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.derk.damagebreakdown.model.JSONHelper.getMatchTelemetry;

/**
 * Class for interacting with PUBG API
 */
public class API {
    private static String REGION_PREFIX = "https://api.playbattlegrounds.com/shards/pc-na";

    static void getUserData(final String username, final Callback<JSONObject>  callback){
        final String prefix = "/players?filter[playerNames]=";
        final String url = prefix + username;

        makeAPIRequest(url, new Callback<JSONObject>(){
            public void onResult(JSONObject result){
                try {
                    callback.onResult(JSONHelper.getUser(result));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    static void lookupMatch(String matchID, final Callback<JSONObject>  callback){
        String url = "/matches/" + matchID;

        makeAPIRequest(url, new Callback<JSONObject> () {
            @Override
            public void onResult(JSONObject result) {
                try {
                    callback.onResult(getMatchTelemetry(result));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private static void makeAPIRequest(String url, Callback<JSONObject>  callback) {
        JSONFromUrlTask task = new JSONFromUrlTask(callback);
        task.execute(REGION_PREFIX + url);
    }
}
