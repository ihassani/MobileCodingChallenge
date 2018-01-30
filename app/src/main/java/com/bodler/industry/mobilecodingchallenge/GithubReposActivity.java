package com.bodler.industry.mobilecodingchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bodler.industry.mobilecodingchallenge.models.Repository;
import com.bodler.industry.mobilecodingchallenge.models.SearchResult;
import com.bodler.industry.mobilecodingchallenge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubReposActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;

    public RecyclerView reposRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repos);

        reposRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        reposRecyclerView.setLayoutManager(layoutManager);

        Call<SearchResult<Repository>> callGetAllRepos = Utils.getWS().getAllRepos("created:>" + Utils.getDateDaysAgo(30), "stars", "desc");

        callGetAllRepos.enqueue(new Callback<SearchResult<Repository>>() {
            @Override
            public void onResponse(Call<SearchResult<Repository>> call, Response<SearchResult<Repository>> response) {
                Log.e("success", response.body().getItems().get(0).getName() + "");

                ArrayList<Repository> items = response.body().getItems();

                ReposAdapter reposAdapter = new ReposAdapter(items, getApplicationContext());

                reposRecyclerView.setAdapter(reposAdapter);
            }

            @Override
            public void onFailure(Call<SearchResult<Repository>> call, Throwable t) {
                Log.e("failure", t.getMessage());
            }
        });
    }
}
