package com.derk.damagebreakdown.model;

import android.os.Handler;
import android.util.Log;

import com.derk.damagebreakdown.controller.Callback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for interacting with PUBG API
 */
class API {
    private static final int MAX_REQUESTS = 8;
    private static final int REQUEST_RESET_TIME = 60000;
    private static int request_count = 0;

    private static final String REGION_PREFIX = "https://api.playbattlegrounds.com/shards/pc-na";

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
                callback.onResult(result);
            }
        });
    }

    static void lookUpTelemetry(String url, Callback<JSONObject> callback){
        callback.onResult(new JSONObject());
    }

    private static void makeAPIRequest(String url, Callback<JSONObject>  callback) {
        if(request_count >= MAX_REQUESTS){
            Log.i("API", "API Limit reached");
            callback.onResult(null);
            return;
        }
        request_count += 1;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                request_count--;
            }
        }, REQUEST_RESET_TIME);
        JSONFromUrlTask task = new JSONFromUrlTask(callback);
        task.execute(REGION_PREFIX + url);
    }
}
