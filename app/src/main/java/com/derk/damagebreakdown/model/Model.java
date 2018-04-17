package com.derk.damagebreakdown.model;


import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Model {
    private static String REGION_PREFIX = "https://api.playbattlegrounds.com/shards/pc-na";
    private static String TAG = "derkberklin";

    public void refresh(){
        String url = "/players?filter[playerNames]=" + TAG;
        makeAPIRequest(url, new APICallback(){
            public void onResult(String result){
                System.out.println(result);
            }
        });
    }

    private void makeAPIRequest(String url, APICallback callback) {
        APITask task = new APITask(callback);
        task.execute(REGION_PREFIX + url);
    }

    private class APITask extends AsyncTask<String, Void, String> {
        private APICallback callback;

        private APITask(APICallback callback){
            this.callback = callback;
        }
        protected String doInBackground(String... urls) {
            String result = null;
            InputStream is = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization","Bearer " + Secrets.API_KEY);
                conn.setRequestProperty("Accept", "application/json");

                is = conn.getInputStream();
                result = IOUtils.toString(is, "UTF-8");
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callback.onResult(result);
        }
    }
}
