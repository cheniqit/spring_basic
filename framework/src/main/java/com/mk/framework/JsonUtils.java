package com.mk.framework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by kirinli on 16/2/18.
 */
public class JsonUtils {
    public static String toJson(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public static String toJson(Object src, String format) {
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {

        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, String format, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setDateFormat(format).create();
        return gson.fromJson(json, classOfT);
    }



}
