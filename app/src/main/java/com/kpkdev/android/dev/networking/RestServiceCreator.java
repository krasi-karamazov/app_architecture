package com.kpkdev.android.dev.networking;

import com.kpkdev.android.dev.utils.Constants;
import com.kpkdev.android.dev.utils.Logger;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class RestServiceCreator {
    private static RestAdapter adapter;

    private static RestAdapter getRestAdapter() {

        if (adapter == null) {
            RestAdapter.Builder adapterBuilder = new RestAdapter.Builder()
                    .setEndpoint(Constants.ENDPOINT)
                    .setClient(new OkClient(getClient()))
                    .setConverter(new GsonConverter(InboxDollarsJsonBuilder.gson()));

            if (Logger.DEBUG) {
                adapterBuilder.setLog(new RestAdapter.Log() {
                    @Override public void log(String s) {
                        Logger.d(s);
                    }
                })
                        .setLogLevel(RestAdapter.LogLevel.FULL);
            } else {
                adapterBuilder.setLogLevel(RestAdapter.LogLevel.NONE);
            }
            adapter = adapterBuilder.build();
        }
        return adapter;
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(15 * 1000, TimeUnit.SECONDS);
        client.setReadTimeout(15 * 1000, TimeUnit.SECONDS);
        return client;
    }
}
