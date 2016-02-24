package com.mk.taskfactory.biz.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.model.crawer.QHotel;

import java.util.*;

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

    public static void main(String arg[]){
        String s = "[{\"name\":\"def\",\"pic\":[{\"url\":\"https://dn-imke-pro.qbox.me/FikvzvbTN_r9mWewxelSdxtxIjnw\"}]},{\"name\":\"lobby\",\"pic\":[]},{\"name\":\"mainHousing\",\"pic\":[]}]";
        JSONArray jsonArray = JSONArray.parseArray(s);
        System.out.println(jsonArray.get(0));
        Object dd =jsonArray.get(0);
        final QHotel qHotel = new QHotel();
        qHotel.setAreaCode(22);
        String  object1 = JSONObject.toJSONString(qHotel);
        System.out.println(object1);

    }
    /* 将Json对象转换成Map
    *
    * @param jsonObject
    *            json对象
    * @return Map对象
    * @throws JSONException
    */
    public static Map<String,String> jsonToMap(String jsonString) throws JSONException {
        JSONObject object = JSONObject.parseObject(jsonString);
        Map<String,String> result = new HashMap<String,String>();
        for (String key:object.keySet()){
            if (object.get(key)!=null)
                result.put(key,object.get(key).toString());
        }
        return result;

    }
    /* 将Json对象转换成List
   *
   * @param jsonList
   *            json对象
   * @return List对象
   * @throws JSONException
   */
    public static List<Object> jsonToList(String jsonString){
        List<Object> result = new ArrayList<Object>();
        try{
            JSONArray jsonArray =JSONArray.parseArray(jsonString);
            for (Object obj:jsonArray.toArray()){
                result.add(obj);
            }
        }catch (JSONException e){
            return null;
        }
        return result;

    }

    /* 将object对象转换成json
  *
  * @param object
  * @return json对象
  * @throws JSONException
  */
    public static String toJSONString(Object bean) throws JSONException {
        String result =null;
        try{
            result =JSONArray.toJSONString(bean);
        }catch (JSONException e){
            return null;
        }
        return result;

    }

    /* 将json to Class<T>
      *
      * @param object
      * @return json对象
      * @throws JSONException
      */
    public static <T>T formatJson(String json, Class<T> classOfT) throws JSONException {
        return JSON.parseObject(json,classOfT);
    }
}
