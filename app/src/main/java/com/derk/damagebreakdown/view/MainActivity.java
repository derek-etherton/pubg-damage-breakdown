package com.derk.damagebreakdown.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.derk.damagebreakdown.R;
import com.derk.damagebreakdown.controller.Controller;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_matches;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller();

        rv_matches = (RecyclerView) findViewById(R.id.rv_matches);
        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.refresh();
            }
        });
    }
}
