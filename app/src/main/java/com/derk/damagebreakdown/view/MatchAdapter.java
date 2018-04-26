package com.derk.damagebreakdown.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derk.damagebreakdown.R;
import com.derk.damagebreakdown.model.Match;

import java.util.List;


class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<Match> matches;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    MatchAdapter(List<Match> matches){
        this.matches = matches;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(matches.get(position).getMatchID());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_match, parent, false);

        ViewHolder vh = new ViewHolder(tv);

        return vh;
    }
}
