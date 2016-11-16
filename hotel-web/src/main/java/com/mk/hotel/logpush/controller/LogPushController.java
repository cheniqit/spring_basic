package com.mk.hotel.logpush.controller;

import com.mk.execution.pushinfo.JobManager;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/logpush", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogPushController {

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/logpush", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> logPush(String start, String end, Long logId) {

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = null;
        Date dateEnd = null;

        try {
            dateStart = format.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            dateEnd = format.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //
        List<LogPushDto> logPushList = this.logPushService.getByTime(dateStart, dateEnd, logId );
        for(LogPushDto logPushDto : logPushList) {
            Long typeId = logPushDto.getType();
            String body = logPushDto.getMsg();

            LogPushTypeEnum type = LogPushTypeEnum.getById(typeId);
            JobManager.addPushInfoToRefreshJob(body, type);
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

