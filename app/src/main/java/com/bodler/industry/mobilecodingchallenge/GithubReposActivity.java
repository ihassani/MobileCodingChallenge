package com.bodler.industry.mobilecodingchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bodler.industry.mobilecodingchallenge.models.Repository;
import com.bodler.industry.mobilecodingchallenge.models.SearchResult;
import com.bodler.industry.mobilecodingchallenge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubReposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repos);

        Call<SearchResult<Repository>> callGetAllRepos = Utils.getWS().getAllRepos("created:>2017-12-27", "stars", "desc");

        callGetAllRepos.enqueue(new Callback<SearchResult<Repository>>() {
            @Override
            public void onResponse(Call<SearchResult<Repository>> call, Response<SearchResult<Repository>> response) {
                Log.e("success", response.body().getItems().get(0).getName() + "");
            }

            @Override
            public void onFailure(Call<SearchResult<Repository>> call, Throwable t) {
                Log.e("failure", t.getMessage());
            }
        });
    }
}
