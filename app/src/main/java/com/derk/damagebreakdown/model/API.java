package com.derk.damagebreakdown.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.derk.damagebreakdown.model.JSONHelper.getMatchTelemetry;

/**
 * Class for interacting with PUBG API
 */
public class API {
    private static String REGION_PREFIX = "https://api.playbattlegrounds.com/shards/pc-na";

    static void getUserData(final String username, final APICallback callback){
        final String prefix = "/players?filter[playerNames]=";
        final String url = prefix + username;

        makeAPIRequest(url, new APICallback(){
            public void onResult(JSONObject result){
                try {
                    callback.onResult(JSONHelper.getUser(result));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    static void lookupMatch(String matchID, final APICallback callback){
        String url = "/matches/" + matchID;
        makeAPIRequest(url, new APICallback() {
            @Override
            public void onResult(JSONObject result) {
                try {
                    JSONObject telemetry = getMatchTelemetry(result);
                    callback.onResult(telemetry);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private static void makeAPIRequest(String url, APICallback callback) {
        JSONFromUrlTask task = new JSONFromUrlTask(callback);
        task.execute(REGION_PREFIX + url);
    }
}
