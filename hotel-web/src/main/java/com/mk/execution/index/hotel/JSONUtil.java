package com.mk.execution.index.hotel;

import com.google.gson.Gson;

/**
 * Created by 振涛 on 2016/2/18.
 */
class JSONUtil {

    static String toJson(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    static <T> T fromJson(String json, Class<T> classOfT) {

        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

}
