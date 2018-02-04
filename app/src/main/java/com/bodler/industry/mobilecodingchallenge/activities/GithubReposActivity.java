package com.bodler.industry.mobilecodingchallenge.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bodler.industry.mobilecodingchallenge.R;
import com.bodler.industry.mobilecodingchallenge.adapters.ReposAdapter;
import com.bodler.industry.mobilecodingchallenge.models.Repository;
import com.bodler.industry.mobilecodingchallenge.models.SearchResult;
import com.bodler.industry.mobilecodingchallenge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubReposActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;

    private RecyclerView reposRecyclerView;

    private ReposAdapter reposAdapter;

    private ProgressBar loadingProgressBar;

    private int itemsPerPage = 30;
    private int currentPage = 1;
    private boolean isLoading = false;

    private RecyclerView.OnScrollListener reposRecyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= itemsPerPage * currentPage) {
                    loadMoreItems();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repos);

        loadingProgressBar = (ProgressBar) findViewById(R.id.loading_progressbar);

        reposRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.addOnScrollListener(reposRecyclerViewOnScrollListener);

        Call<SearchResult<Repository>> callGetAllRepos = Utils.getWS().getAllRepos("created:>" + Utils.getDateDaysAgo(30), "stars", "desc", currentPage + "");

        callGetAllRepos.enqueue(new Callback<SearchResult<Repository>>() {
            @Override
            public void onResponse(Call<SearchResult<Repository>> call, Response<SearchResult<Repository>> response) {
//                Log.e("success", response.toString());
                if (response.body() != null) {
                    ArrayList<Repository> items = response.body().getItems();

                    reposAdapter = new ReposAdapter(items, getApplicationContext());
                    reposRecyclerView.setAdapter(reposAdapter);
                }
            }
            @Override
            public void onFailure(Call<SearchResult<Repository>> call, Throwable t) {
//                Log.e("failure", t.getMessage());
            }
        });



    }

    private void loadMoreItems() {
        isLoading = true;

        loadingProgressBar.setVisibility(View.VISIBLE);

        currentPage ++;

        Call<SearchResult<Repository>> callGetAllRepos = Utils.getWS().getAllRepos("created:>" + Utils.getDateDaysAgo(30), "stars", "desc", currentPage + "");

        callGetAllRepos.enqueue(new Callback<SearchResult<Repository>>() {
            @Override
            public void onResponse(Call<SearchResult<Repository>> call, Response<SearchResult<Repository>> response) {
//                Log.e("success1", response.toString());
                if (response.body() != null) {
                    ArrayList<Repository> items = response.body().getItems();

                    reposAdapter.addItems(items);
                    reposAdapter.notifyDataSetChanged();

                } else {
                    currentPage--;
                }
                loadingProgressBar.setVisibility(View.GONE);
                isLoading = false;
            }
            @Override
            public void onFailure(Call<SearchResult<Repository>> call, Throwable t) {
//                Log.e("failure", t.getMessage());
            }
        });


    }
}
