package com.derk.damagebreakdown.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    static List<Match> createMatchList(JSONArray matchesArray) throws JSONException {
        List<Match> matches = new ArrayList<>();

        int i = 0;
        JSONObject obj = matchesArray.optJSONObject(i);
        while (obj != null){
            if (obj.get("type").equals("match")) {
                matches.add(createMatch(obj));
            }
            i++;
            obj = matchesArray.optJSONObject(i);
        }

        return matches;
    }

    static Match createMatch(JSONObject matchObj) throws JSONException {
        Match match = new Match(matchObj.get("id").toString());
        // TODO: populate other fields
        return match;
    }

}
