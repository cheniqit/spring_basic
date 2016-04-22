package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.CrawerToOtsService;
import com.mk.taskfactory.api.OrderSendJobService;
import com.mk.taskfactory.api.crawer.CrawerCommentImgService;
import com.mk.taskfactory.api.crawer.CrawerHotelImageService;
import com.mk.taskfactory.api.dtos.OrderDto;
import com.mk.taskfactory.api.dtos.OrderRecommendInfoDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.OrderToCsLogDto;
import com.mk.taskfactory.api.dtos.crawer.TExCommentImgDto;
import com.mk.taskfactory.api.dtos.crawer.TExHotelImageDto;
import com.mk.taskfactory.api.ots.*;
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
    private OrderToCsLogService orderToCsLogService;

    @Autowired
    private OrderRecommendInfoService orderRecommendInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UMemberMapper memberMapper;
    public void  orderSendToCs(){
        OrderToCsDto bean = new OrderToCsDto();
        orderSendToCs(bean);
    }
    public Map<String,Object> orderSendToCs(OrderToCsDto bean){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("orderSendToCs","orderSendToCs",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info("OrderSendJobServiceImpl.orderSendToCs start:{}",DateUtils.format_yMdHms(new Date()));

        bean.setCount(5);
        List<OrderToCsDto> orderToCsDtoList = orderToCsService.qureySendList(bean);
        if (CollectionUtils.isEmpty(orderToCsDtoList)){
            logger.info("OrderSendJobServiceImpl.orderSendToCs orderToCsDtoList.size: 0 ");
            resultMap.put("message","orderToCsDtoList count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        logger.info("OrderSendJobServiceImpl.orderSendToCs orderToCsDtoList.size: {} ", orderToCsDtoList.size());

        List<OrderToCsBean> beanList = new ArrayList<OrderToCsBean>();
        Map<Long,OrderToCsDto> orderMap = new HashMap<Long, OrderToCsDto>();
        for (OrderToCsDto orderToCsDto:orderToCsDtoList){
            logger.info("OrderSendJobServiceImpl.orderSendToCs orderId:{} ", orderToCsDto.getOrderId());
            OrderDto order = new OrderDto();
            order.setId(orderToCsDto.getOrderId());
            order = orderService.getByPramas(order);
            if (order==null||order.getId()==null){
                continue;
            }

            //
//            UMember member = new UMember();
//            member.setMid(order.getmId());
//            member = memberMapper.selectByMid(member);
//            if (member==null||member.getMid()==null){
//                continue;
//            }

            //
            String isRecommandOrder = "F";
            List<OrderRecommendInfoDto> infoDtoList = orderRecommendInfoService.selectByNewOrderId(order.getId());
            if (!infoDtoList.isEmpty()) {
                isRecommandOrder = "T";
            }

            OrderToCsBean setBean = new OrderToCsBean();
            setBean.setOrderId(order.getId());
            setBean.setLiveUserPhone(order.getContactsPhone());
//            setBean.setOrderUserPhone(member.getPhone());
            setBean.setIsRecommandOrder(isRecommandOrder);
            setBean.setOrderStatus(order.getStatus());

            setBean.setCreateTime(DateUtils.format_yMdHms(order.getCreateTime()));
            beanList.add(setBean);
            orderMap.put(orderToCsDto.getOrderId(),orderToCsDto);
        }
        if (CollectionUtils.isEmpty(beanList)){
            resultMap.put("message","beanList is empty");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        Map<String, String> params=new HashMap<String, String>();
        params.put("orders", JsonUtils.toJSONString(beanList));
        String postResult= HttpUtils.doPost(Constants.CS_URL + "/custom/order/addorders", params);
        logger.info("OrderSendJobServiceImpl.orderSendToCs postResult:{} ", postResult);

        //记录返回值
//        for (Long key:orderMap.keySet()) {
//            OrderToCsDto updateBean = orderMap.get(key);
//
//            updateBean.setCount(updateBean.getCount()+1);
//            updateBean.setExecuteTime(new Date());
//            updateBean.setResult(postResult);
//            orderToCsService.updateById(updateBean);
//        }

        try {
            OrderToCsLogDto logDto = new OrderToCsLogDto();
            logDto.setSend(JsonUtils.toJSONString(beanList));
            logDto.setResult(postResult);
            logDto.setCreateTime(new Date());
            logDto.setIsValid("T");
            orderToCsLogService.save(logDto);
            logger.info("OrderSendJobServiceImpl.orderSendToCs save result success");
        } catch (Exception e) {
            logger.info("OrderSendJobServiceImpl.orderSendToCs save result error :{}", e);
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(postResult)){
            resultMap.put("message","请求失败");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        Map<String,String> returnMap = JsonUtils.jsonToMap(postResult);
        if(CollectionUtils.isEmpty(returnMap)){
            resultMap.put("message","解析返回结果失败");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        if (StringUtils.isEmpty(returnMap.get("success"))&& "false".equals(returnMap.get("success"))){
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
        logger.info("OrderSendJobServiceImpl.orderSendToCs update status ");
        Cat.logEvent("orderSendToCs", "orderSendToCs", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );

        logger.info("OrderSendJobServiceImpl.orderSendToCs end:{} ",DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public void  bookingOkJob(){
        HttpUtils.get_data(Constants.OTS_URL + "/order/testjob","GET");
    }
}
