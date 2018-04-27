package com.derk.damagebreakdown.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derk.damagebreakdown.R;
import com.derk.damagebreakdown.controller.Callback;
import com.derk.damagebreakdown.controller.Controller;
import com.derk.damagebreakdown.model.Match;

import org.json.JSONObject;

import java.util.List;


class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<String> matchIDs;
    private Controller controller;
    private OnMatchClickListener onMatchClickListener;

    interface OnMatchClickListener {
        void onClick(String matchID);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    MatchAdapter(List<String> matchIDs, OnMatchClickListener listener){
        this.onMatchClickListener = listener;
        controller = new Controller();
        this.matchIDs = matchIDs;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(matchIDs.get(position) == null){
            holder.mTextView.setText(R.string.match_missing);
            return;
        }

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();
                String matchId = matchIDs.get(i);
                onMatchClickListener.onClick(matchId);
            }
        });

        controller.lookUpMatchById(matchIDs.get(position), new Callback<Match>() {
            @Override
            public void onResult(Match result) {
                if (result != null) {
                    holder.mTextView.setText(result.getDate() + "   " + result.getTime());
                } else {
                    holder.mTextView.setText(R.string.match_missing);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (matchIDs == null){
            return 0;
        }
        return matchIDs.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_match, parent, false);

        ViewHolder vh = new ViewHolder(tv);

        return vh;
    }
}
