package com.ryandro.covid_19_tracker.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 */

public class NetworkUtility {

    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isNetworkAvailable = networkInfo.getState() == NetworkInfo.State.CONNECTED;
        }
        return isNetworkAvailable;
    }
}
