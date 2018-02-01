package com.bodler.industry.mobilecodingchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bodler.industry.mobilecodingchallenge.models.Repository;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ibrahim on 28/01/2018.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ItemRowHolder> {

    private ArrayList<Repository> reposList;
    private Context context;

    public ReposAdapter(ArrayList<Repository> reposList, Context context) {
        this.reposList = reposList;
        this.context = context;
    }

    @Override
    public ReposAdapter.ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_layout, parent, false);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ReposAdapter.ItemRowHolder holder, int position) {

        Repository repo = reposList.get(position);

        holder.repoNameTextView.setText(repo.getName());
        holder.repoDescriptionTextView.setText(repo.getDescription());

        Log.e("repoadapter", repo.getOwner().getLogin());
        holder.repoOwnerUsernameTextView.setText(repo.getOwner().getLogin());
        holder.repoStarCountTextView.setText(repo.getStargazersCount());

        Glide.with(context).load(repo.getOwner().getAvatarURL()).into(holder.repoOwnerAvatarImageView);
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public void addItems(ArrayList<Repository> list) {
        if (list != null) {
            reposList.addAll(list);
        }
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView repoNameTextView;
        protected TextView repoDescriptionTextView;
        protected TextView repoOwnerUsernameTextView;
        protected TextView repoStarCountTextView;

        protected ImageView repoOwnerAvatarImageView;





        public ItemRowHolder(View view) {
            super(view);

            this.repoNameTextView = (TextView) view.findViewById(R.id.name_id);
            this.repoDescriptionTextView = (TextView) view.findViewById(R.id.description_id);
            this.repoOwnerUsernameTextView = (TextView) view.findViewById(R.id.username_id);
            this.repoStarCountTextView = (TextView) view.findViewById(R.id.stars_id);

            this.repoOwnerAvatarImageView = (ImageView) view.findViewById(R.id.avatar_imageview);

        }

    }
}
