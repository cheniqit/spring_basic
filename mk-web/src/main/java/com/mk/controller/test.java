package com.mk.controller;

import com.mk.common.CommonResponse;
import com.mk.common.PageObject;
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
public class test {
    @RequestMapping(value = "/page")
    public CommonResponse page(){
        CommonResponse response = new CommonResponse();
        List<String> list = new ArrayList<String>();
        list.add("no");
        PageObject pageObject = new PageObject(5, list);
        response.setData(pageObject);
        return response;
    }
}
