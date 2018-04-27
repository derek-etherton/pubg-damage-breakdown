package com.derk.damagebreakdown.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JSONHelper {
    /** TODO: LIMIT 3 IS TEMPORARY */
    private static final int MAX_MATCHIDS = 3;

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

    static List<String> getMatchIDs(JSONArray matchesArray) throws JSONException {
        List<String> ids = new ArrayList<>();

        int i = 0;
        int matchCount = 0;
        JSONObject obj = matchesArray.optJSONObject(i);
        while (obj != null && matchCount < MAX_MATCHIDS){
            if (obj.get("type").equals("match")) {
                String id = obj.get("id").toString();
                ids.add(id);
                matchCount++;
            }
            i++;
            obj = matchesArray.optJSONObject(i);
        }

        return ids;
    }

    static Match createMatch(JSONObject matchObj) throws JSONException {
        if (matchObj == null){
            return null;
        }

        JSONObject data = matchObj.getJSONObject("data");
        System.out.println(data);
        Match match = new Match(data.getString("id"));

        JSONObject atts = data.getJSONObject("attributes");
        //String telemetryID = assets.getJSONObject("assets").getJSONArray("data").getJSONObject(0).get("id").toString();
        match.setCreatedAt(atts.getString("createdAt"));
        match.setGameMode(atts.getString("gameMode"));
        // TODO: populate other fields
        return match;
    }

}
