package com.mk.hotel.order.controller;

import com.dianping.cat.Cat;
import com.mk.framework.JsonUtils;
import com.mk.hotel.common.enums.PmsOrderStatusEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.order.controller.json.OrderStatusPush;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/orderstatus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> orderStatusPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.orderStatus.getId());
            this.logPushService.save(logPushDto);
            HashMap<String,Object> result= new LinkedHashMap<String, Object>();
            if(StringUtils.isBlank(body)){
                result.put("success", "F");
                result.put("errorCode", "参数为空");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            OrderStatusPush orderStatusPush = JsonUtils.fromJson(body, OrderStatusPush.class);
            if(orderStatusPush == null
                    || StringUtils.isNotBlank(orderStatusPush.getOrderid())
                    || StringUtils.isNotBlank(orderStatusPush.getOrderstatus())){
                result.put("success", "F");
                result.put("errorCode", "参数为空");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            //状态判断
            Integer pmsOrderStatus = Integer.valueOf(orderStatusPush.getOrderstatus());
            if(PmsOrderStatusEnum.checkIn.getCode().equals(pmsOrderStatus)){
                OtsInterface.updateOrderStatusByPms(orderStatusPush.getOrderid(), pmsOrderStatus);
            }
            if(PmsOrderStatusEnum.channelCanceled.getCode().equals(pmsOrderStatus)
                    || PmsOrderStatusEnum.pmsCanceled.getCode().equals(pmsOrderStatus)
                    || PmsOrderStatusEnum.customerServiceCanceled.getCode().equals(pmsOrderStatus)
                    || PmsOrderStatusEnum.serviceCanceled.getCode().equals(pmsOrderStatus)){
                OtsInterface.updateOrderStatusByPms(orderStatusPush.getOrderid(), pmsOrderStatus);
            }
            if(PmsOrderStatusEnum.noshow.getCode().equals(pmsOrderStatus)){
                OtsInterface.updateOrderStatusByPms(orderStatusPush.getOrderid(), pmsOrderStatus);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

