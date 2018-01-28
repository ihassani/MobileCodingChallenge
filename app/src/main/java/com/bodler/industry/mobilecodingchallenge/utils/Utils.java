package com.bodler.industry.mobilecodingchallenge.utils;

import com.bodler.industry.mobilecodingchallenge.WS.WS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bodler.industry.mobilecodingchallenge.utils.AppConstants.GIT_HUB_SERVER_ADDRESS;

/**
 * Created by ibrahim on 27/01/2018.
 */

public class Utils {

    public static WS service ;

    public static WS getWS() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GIT_HUB_SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            service = retrofit.create(WS.class);
        }
        return service;
    }
}
