package com.mk.motan.controller;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/motan", produces = MediaType.APPLICATION_JSON_VALUE)
public class MotanController {

    @RequestMapping(value = "/online", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> online() {

        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/offline", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> offline() {

        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, false);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
