package com.bodler.industry.mobilecodingchallenge.WS;

import com.bodler.industry.mobilecodingchallenge.models.Repository;
import com.bodler.industry.mobilecodingchallenge.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ibrahim on 27/01/2018.
 */

public interface WS {
    @GET("repositories")
    Call<SearchResult<Repository>> getAllRepos(
            @Query("q") String q, @Query("sort") String sort, @Query("order") String order);
}
