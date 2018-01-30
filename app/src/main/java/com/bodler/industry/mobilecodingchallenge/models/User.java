package com.bodler.industry.mobilecodingchallenge.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ibrahim on 27/01/2018.
 */

public class User {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("login")
    @Expose
    String login;

    public int getId() {
        return id;
    }

    @JsonProperty("avatar_url")
    @SerializedName("avatar_url")

    @Expose
    String avatarURL;



    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

}
