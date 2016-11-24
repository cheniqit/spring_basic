package com.mk.controller;

import com.mk.common.CommonRequest;
import com.mk.common.CommonResponse;
import com.mk.common.PageObject;
import com.mk.framework.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 16/11/23.
 */
@RestController
@RequestMapping(value = "/test" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/page")
    public CommonResponse page(CommonRequest request){
        logger.info("request params {}", JsonUtils.toJson(request));
        CommonResponse response = new CommonResponse();
        List<String> list = new ArrayList<String>();
        list.add("no");
        PageObject pageObject = new PageObject(5, list);
        response.setData(pageObject);
        logger.info("response{}", JsonUtils.toJson(pageObject));
        return response;
    }
}
