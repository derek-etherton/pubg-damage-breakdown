package com.derk.damagebreakdown.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONHelper {
    static JSONObject getUser(JSONObject data) throws JSONException {
        return data.getJSONArray("data").getJSONObject(0);
    }

    static JSONArray getMatches(JSONObject user) throws JSONException {
        return user.getJSONObject("relationships").getJSONObject("matches").getJSONArray("data");
    }

    static JSONObject getMatchTelemetry(JSONObject match) throws JSONException {
        // TODO: Break up into several methods
        JSONObject assets = match.getJSONObject("data").getJSONObject("relationships");
        String telemetryID = assets.getJSONObject("assets").getJSONArray("data").getJSONObject(0).get("id").toString();
        System.out.println(telemetryID);

        for (int i = 0 ; i < 131; i ++){
            String type = match.getJSONArray("included").getJSONObject(i).getString("type");
            if (type.equals("asset")){
                System.out.println(match.getJSONArray("included").getJSONObject(i));
                System.out.println(i);
                break;
            }
        }
        return new JSONObject();
    }

    static String getMatchID(JSONObject match) throws JSONException {
        return match.get("id").toString();
    }

}
