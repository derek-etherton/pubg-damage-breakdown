package com.derk.damagebreakdown.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derk.damagebreakdown.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchDetailsFragment extends Fragment {
    private static final String TELEMETRY_TAG = "telemetry";
    private JSONObject telemetry;

    public static MatchDetailsFragment newInstance(JSONObject telemetry){
        MatchDetailsFragment fragment = new MatchDetailsFragment();

        Bundle args = new Bundle();
        args.putString(TELEMETRY_TAG, telemetry.toString());
        fragment.setArguments(args);

        return fragment;
    }

    public MatchDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            telemetry = new JSONObject(getArguments().getString(TELEMETRY_TAG));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_match_details, container, false);

        ((TextView)rootView.findViewById(R.id.tv_match_id)).setText("testo");

        return rootView;
    }

}
