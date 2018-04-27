package com.derk.damagebreakdown.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.derk.damagebreakdown.R;
import com.derk.damagebreakdown.controller.Callback;
import com.derk.damagebreakdown.controller.Controller;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_matches;
    Controller controller;
    MatchAdapter.OnMatchClickListener matchClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller();

        rv_matches = (RecyclerView) findViewById(R.id.rv_matches);
        rv_matches.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        matchClickListener = new MatchAdapter.OnMatchClickListener() {
            public void onClick(String matchID) {
                final Callback<JSONObject> callback = new Callback<JSONObject>() {
                    @Override
                    public void onResult(JSONObject result) {
                        if (result == null){
                            Toast.makeText(getApplicationContext(), R.string.match_missing,
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Fragment fragment = MatchDetailsFragment.newInstance(result);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment).addToBackStack("match").commit();
                    }
                };

                controller.getTelemetryData(matchID, callback);
            }
        };

        final Callback<List<String>> refreshCallback = new Callback<List<String>>() {
            @Override
            public void onResult(List<String> result) {
                rv_matches.setAdapter(new MatchAdapter(result, matchClickListener));
            }
        };

        final View.OnClickListener onRefreshClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.refreshList(refreshCallback);
            }
        };

        findViewById(R.id.btn_refresh).setOnClickListener(onRefreshClicked);
    }
}
