package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.CrawerToOtsService;
import com.mk.taskfactory.api.OrderSendJobService;
import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.crawer.CrawerHotelImageService;
import com.mk.taskfactory.api.dtos.OrderDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.api.ots.OrderService;
import com.mk.taskfactory.api.ots.OrderToCsService;
import com.mk.taskfactory.api.ots.OtsCommentImgService;
import com.mk.taskfactory.api.ots.OtsHotelImageService;
import com.mk.taskfactory.biz.mapper.ots.UMemberMapper;
import com.mk.taskfactory.biz.umember.model.UMember;
import com.mk.taskfactory.biz.utils.*;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.Order;
import com.mk.taskfactory.model.OrderToCsBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class OrderSendJobServiceImpl implements OrderSendJobService {
    private static Logger logger = LoggerFactory.getLogger(OrderSendJobServiceImpl.class);

    @Autowired
    private OrderToCsService orderToCsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UMemberMapper memberMapper;

    public Map<String,Object> orderSendToCs(OrderToCsDto bean){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("orderSendToCs","orderSendToCs",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================orderSendToCs begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        bean.setCount(5);
        List<OrderToCsDto> orderToCsDtoList = orderToCsService.qureySendList(bean);
        if (CollectionUtils.isEmpty(orderToCsDtoList)){
            resultMap.put("message","orderToCsDtoList count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        List<OrderToCsBean> beanList = new ArrayList<OrderToCsBean>();
        logger.info(String.format("\n====================send size={}====================\n")
                ,orderToCsDtoList.size());
        Map<Long,OrderToCsDto> orderMap = new HashMap<Long, OrderToCsDto>();
        for (OrderToCsDto orderToCsDto:orderToCsDtoList){
            OrderDto order = new OrderDto();
            order.setId(orderToCsDto.getOrderId());
            order = orderService.getByPramas(order);
            if (order==null){
                continue;
            }
            UMember member = new UMember();
            member.setMid(order.getmId());
            member = memberMapper.selectByMid(member);
            if (member==null){
                continue;
            }
            OrderToCsBean setBean = new OrderToCsBean();
            setBean.setOrderId(order.getId());
            setBean.setLiveUserPhone(order.getContactsPhone());
            setBean.setOrderUserPhone(member.getPhone());
            beanList.add(setBean);
            orderMap.put(orderToCsDto.getOrderId(),orderToCsDto);
        }
        Map<String, String> params=new HashMap<String, String>();
        params.put("orders", JsonUtils.toJSONString(beanList));
        String postResult= HttpUtils.doPost(Constants.CS_URL + "addorders", params);
        if (StringUtils.isEmpty(postResult)){
            resultMap.put("message","请求失败");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        logger.info(postResult);
        Map<String,String> returnMap = JsonUtils.jsonToMap(postResult);
        if(CollectionUtils.isEmpty(returnMap)){
            resultMap.put("message","解析返回结果失败");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        if (StringUtils.isEmpty(returnMap.get("success"))&&returnMap.get("success")=="false"){
            resultMap.put("message",returnMap.get("errormsg"));
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        List<Object> failList = JsonUtils.jsonToList(postResult);
        Map<Long,String> failMap = new HashMap<Long, String>();
        if(!CollectionUtils.isEmpty(failList)){
            for (Object obj:failList){
                if (obj!=null){
                    failMap.put(Long.valueOf(obj.toString()),"1");
                }
            }
        }

        for (Long key:orderMap.keySet()) {
            OrderToCsDto updateBean = orderMap.get(key);
            if (StringUtils.isNotEmpty(failMap.get(updateBean.getOrderId()))){
                updateBean.setStatus(30);
            }else {
                updateBean.setStatus(20);
            }
            updateBean.setCount(updateBean.getCount()+1);
            updateBean.setExecuteTime(new Date());
            orderToCsService.updateById(updateBean);
        }
        Cat.logEvent("orderSendToCs", "orderSendToCs", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================orderSendToCs  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

}
