package com.derk.damagebreakdown.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.derk.damagebreakdown.R;
import com.derk.damagebreakdown.controller.Callback;
import com.derk.damagebreakdown.controller.Controller;
import com.derk.damagebreakdown.model.Match;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_matches;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller();

        rv_matches = (RecyclerView) findViewById(R.id.rv_matches);
        rv_matches.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        final Callback<List<Match>> refreshCallback = new Callback<List<Match>>() {
            @Override
            public void onResult(List<Match> result) {
                System.out.println(result);
                rv_matches.setAdapter(new MatchAdapter(result));
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
