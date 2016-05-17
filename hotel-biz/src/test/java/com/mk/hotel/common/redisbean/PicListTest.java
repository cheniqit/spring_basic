package com.mk.hotel.common.redisbean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chenqi on 16/5/17.
 */
public class PicListTest {
    @Test
    public void testJson(){
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"def\",\n" +
                "    \"pic\": [\n" +
                "      {\n" +
                "        \"url\": \"https://dn-imke.qbox.me/FiaSBlQpHj1kVefDcUvUQxpq_edV\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"https://dn-imke.qbox.me/FpqGuTOjgUb16pzerwbbSikYuckR\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"lobby\",\n" +
                "    \"pic\": [\n" +
                "      {\n" +
                "        \"url\": \"https://dn-imke.qbox.me/FnM4wro8nXqKbtgHVEAeTbxr5HFt\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"url\": \"https://dn-imke.qbox.me/FsHy7aWJpJK2LB0BJPsqRxdZ0I6E\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"mainHousing\",\n" +
                "    \"pic\": [\n" +
                "      {\n" +
                "        \"url\": \"https://dn-imke.qbox.me/FpqGuTOjgUb16pzerwbbSikYuckR\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        ArrayList<PicList> beans = new Gson().fromJson(json, new TypeToken<ArrayList<PicList>>() {
        }.getType());
        Assert.assertNotNull(beans);
    }
}