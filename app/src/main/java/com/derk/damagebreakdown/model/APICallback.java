package com.derk.damagebreakdown.model;

import org.json.JSONObject;

interface APICallback {
    void onResult(JSONObject result);
}
