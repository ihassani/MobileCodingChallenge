package com.bodler.industry.mobilecodingchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ibrahim on 28/01/2018.
 */

public class SearchResult<E> {

    @JsonProperty("total_count")
    @SerializedName("total_count")
    @Expose
    long totalCount;

    @JsonProperty("incomplete_results")
    @SerializedName("incomplete_results")
    @Expose
    boolean incompleteResults;

    @JsonProperty("items")
    @SerializedName("items")
    @Expose
    ArrayList<E> items;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public ArrayList<E> getItems() {
        return items;
    }

    public void setItems(ArrayList<E> items) {
        this.items = items;
    }
}
