package com.kpkdev.android.dev.networking;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class InboxDollarsJsonBuilder {
    private static Gson gson;
    public static Gson gson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)    //.LOWER_CASE_WITH_DASHES)
                            //.excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                    .serializeNulls()
                    .create();
        }
        return gson;
    }
}
