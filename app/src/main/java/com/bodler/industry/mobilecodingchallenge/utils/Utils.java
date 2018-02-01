package com.bodler.industry.mobilecodingchallenge.utils;

import com.bodler.industry.mobilecodingchallenge.WS.WS;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public static String getDateDaysAgo(int days) {
        Calendar from = new GregorianCalendar();

        from.add(Calendar.DAY_OF_MONTH, - days);

        int month = from.get(Calendar.MONTH) + 1;
        int day = from.get(Calendar.DAY_OF_MONTH);

        String fromDate = from.get(Calendar.YEAR) + "-" +  (month < 10 ? "0" + month : month)  + "-" + (day < 10 ? "0" + day : day);

        return fromDate;
    }
}
