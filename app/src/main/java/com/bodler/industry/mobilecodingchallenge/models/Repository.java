package com.bodler.industry.mobilecodingchallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ibrahim on 27/01/2018.
 */

public class Repository {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("Owner")
    @Expose
    User Owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }
}
