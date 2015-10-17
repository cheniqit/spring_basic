package com.mk.taskfactory.biz.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

/**
 * Created by admin on 2015/10/17.
 */
public class JsonUtils {
    public static JSONObject parseObject(String jsonString) {
        JSONObject object = JSONObject.parseObject(jsonString);
        return object;
    }

    public static JSONArray parseArray(String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return jsonArray;
    }
}
