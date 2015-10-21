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

    public static void main(String arg[]){
        String s = "{\"$times$\":\"54ms\",\"hotel\":[{\"hotelid\":2409,\"hotelname\":\"宝宝酒庄\",\"hotelrulecode\":1002,\"visible\":\"T\",\"online\":\"T\",\"roomtype\":[{\"roomtypeid\":7619,\"bednum\":2,\"roomtypename\":\"大床\",\"roomtypeprice\":1.00,\"roomtypepmsprice\":199.00,\"vcroomnum\":4,\"bed\":{\"count\":2,\"beds\":[{\"bedtypename\":\"双床房\",\"bedlength\":\"2.00米\"},{\"bedtypename\":\"双床房\",\"bedlength\":\"2.00米\"}]},\"area\":50.00,\"bedtypename\":\"双床房\",\"bedlength\":\"2.00,2.00米\",\"bathroomtype\":\"公共卫浴\",\"infrastructure\":[{\"infrastructureid\":39,\"infrastructurename\":\"电视\"},{\"infrastructureid\":38,\"infrastructurename\":\"免费茶包\"},{\"infrastructureid\":37,\"infrastructurename\":\"电热水壶\"},{\"infrastructureid\":36,\"infrastructurename\":\"浴巾\"},{\"infrastructureid\":35,\"infrastructurename\":\"毛巾\"},{\"infrastructureid\":34,\"infrastructurename\":\"免费wifi\"},{\"infrastructureid\":33,\"infrastructurename\":\"有线宽带\"},{\"infrastructureid\":32,\"infrastructurename\":\"暖气\"},{\"infrastructureid\":31,\"infrastructurename\":\"空调\"}],\"valueaddedfa\":[{\"valueaddedfaid\":28,\"valueaddedfaname\":\"茶具\"},{\"valueaddedfaid\":40,\"valueaddedfaname\":\"电脑\"},{\"valueaddedfaid\":41,\"valueaddedfaname\":\"书桌\"},{\"valueaddedfaid\":42,\"valueaddedfaname\":\"插线板\"},{\"valueaddedfaid\":43,\"valueaddedfaname\":\"地板\"},{\"valueaddedfaid\":53,\"valueaddedfaname\":\"地砖\"},{\"valueaddedfaid\":54,\"valueaddedfaname\":\"地毯\"}],\"rooms\":[{\"roomid\":61220,\"roomno\":\"110\",\"roomname\":\"大床\",\"roomstatus\":\"vc\",\"isselected\":\"F\",\"haswindow\":\"\"},{\"roomid\":61222,\"roomno\":\"114\",\"roomname\":\"大床\",\"roomstatus\":\"vc\",\"isselected\":\"F\",\"haswindow\":\"\"},{\"roomid\":61223,\"roomno\":\"118\",\"roomname\":\"大床\",\"roomstatus\":\"vc\",\"isselected\":\"F\",\"haswindow\":\"\"},{\"roomid\":61226,\"roomno\":\"126\",\"roomname\":\"大床\",\"roomstatus\":\"vc\",\"isselected\":\"F\",\"haswindow\":\"\"}],\"isfocus\":\"F\",\"iscashback\":\"F\",\"cashbackcost\":0,\"vctxt\":\"\"}]}],\"success\":true}";
        System.out.print(JsonUtils.parseObject(s));
    }
}
