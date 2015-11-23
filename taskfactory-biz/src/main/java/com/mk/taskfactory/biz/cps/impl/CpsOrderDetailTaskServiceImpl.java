package com.mk.taskfactory.biz.cps.impl;

import com.alibaba.fastjson.JSONArray;
import com.mk.taskfactory.api.cps.CpsOrderDetailTaskService;
import com.mk.taskfactory.biz.cps.model.CpsChannel;
import com.mk.taskfactory.biz.cps.model.CpsChannelExample;
import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsOrderListExample;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.cps.bean.CpsOrderListSummary;
import com.mk.taskfactory.biz.cps.model.*;
import com.mk.taskfactory.biz.mapper.OtaOrderMapper;
import com.mk.taskfactory.biz.mapper.UMemberMapper;
import com.mk.taskfactory.biz.cps.mapper.CpsChannelMapper;
import com.mk.taskfactory.biz.cps.mapper.CpsOrderListMapper;
import com.mk.taskfactory.biz.cps.mapper.CpsOrderSummaryCollectMapper;
import com.mk.taskfactory.biz.cps.mapper.CpsRateConfigMapper;
import com.mk.taskfactory.biz.order.impl.OtaOrderServiceImpl;
import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.umember.model.UMember;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.common.exception.CpsException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CpsOrderDetailTaskServiceImpl implements CpsOrderDetailTaskService {
    private static Logger logger = LoggerFactory.getLogger(CpsOrderDetailTaskServiceImpl.class);

    @Autowired
    private CpsOrderListMapper cpsOrderMapper;

    @Autowired
    private CpsChannelMapper cpsChannelMapper;

    @Autowired
    private  UMemberMapper umemberMapper;

    @Autowired
    private  CpsOrderListMapper  cpsOrderListMapper;

    @Autowired
    private OtaOrderServiceImpl otaOrderServiceImpl;

    @Autowired
    private CpsRateConfigMapper cpsRateConfigMapper;

    @Autowired
    private CpsOrderSummaryCollectMapper cpsOrderSummaryCollectMapper;

    @Autowired
    private OtaOrderMapper otaOrderMapper;


    @Transactional("cps")
    public void cpsOrderProduce(){
        logger.info(" 开始执行 cpsOrderProduce、、、、、、、、、、、、、begin");
        CpsChannelExample cpsChannelExample = new CpsChannelExample();
        cpsChannelExample.createCriteria().andValidEqualTo(1);
        List<CpsChannel>  cpsChannelList = cpsChannelMapper.selectByExample(cpsChannelExample);
        if(CollectionUtils.isEmpty(cpsChannelList)){
            logger.info(" query  cpsChannel : [ cpsChannelList = null ]  " );
            return ;
        }
        logger.info(" query  cpsChannel result: " + JSONArray.toJSON(cpsChannelList).toString());
        for(CpsChannel cpsChannle:cpsChannelList){
            HashMap<Long,UMember> memberMap = new  HashMap<Long,UMember>();
            String  channelCode = cpsChannle.getChannelcode();
            if(StringUtils.isEmpty(channelCode)){
                continue;
            }
            HashMap  hmap = new HashMap();
            hmap.put("comefrom", channelCode);
            logger.info(" selectCpsUserByComeFrom    parme  comefrom  :  " + channelCode);
            List<UMember>  umemberList =   umemberMapper.selectCpsUserByComeFrom(hmap);
            if(CollectionUtils.isEmpty(cpsChannelList)){
                continue ;
            }
            String mids = "";
            List<Long>  midList = new ArrayList<Long>();
            for(UMember umember:umemberList){
                umember.setCpsName(cpsChannle.getChannelname());
                memberMap.put(umember.getMid(), umember);
                midList.add(umember.getMid());
            }
            Date  maxTime =  cpsOrderListMapper.getMaxCheckOutTime();
            String  maxTimeStr = DateUtils.format_yMdHms(maxTime);
            List<CpsOrderList>  cpsOrderListCollection =queryOrderByMid(midList,maxTimeStr,channelCode,cpsChannle.getChannelname(),cpsChannle.getTypeid());
            saveCpsOrderList(cpsOrderListCollection);
        }
    }

    public   List<CpsOrderList>  queryOrderByMid(List<Long>  midList,String maxTime,String channelCode,String  channelName,Integer typeId){
        if(CollectionUtils.isEmpty(midList)){
            return null;
        }
        List<CpsOrderList>  resultOrderList = new ArrayList<CpsOrderList>();
        HashMap map = new HashMap();
        map.put("midList", midList);
        List<OtaOrder>  orderList = null;
        if(null == maxTime){
            map.put("maxTime",maxTime);
            orderList =  otaOrderMapper.selectByMidLessThenMaxTime(map);
        }else{
            map.put("maxTime", maxTime);
            orderList =  otaOrderMapper.selectByMidMoreThenMaxTime(map);
        }
        if(CollectionUtils.isEmpty(orderList)){
            return null ;
        }
        for(OtaOrder  otaOrder:orderList){
            CpsOrderList   cpsOrderListEntity = new  CpsOrderList();
            cpsOrderListEntity.setMid(otaOrder.getMid());
            cpsOrderListEntity.setChannelcode(channelCode);
            cpsOrderListEntity.setChannelname(channelName);
            cpsOrderListEntity.setChanneltype(typeId);
            cpsOrderListEntity.setUpdateTime(otaOrder.getUpdateTime());
            cpsOrderListEntity.setCreatetime(new Date());
            cpsOrderListEntity.setHotelid(otaOrder.getHotelId());
            cpsOrderListEntity.setIsnew("1");
            cpsOrderListEntity.setOrderid(otaOrder.getId());
            cpsOrderListEntity.setOrderprice(otaOrder.getTotalPrice());
          if(otaOrderServiceImpl.isFirstOrder(otaOrder)){
            cpsOrderListEntity.setIsfirst("1");
          }else{
              cpsOrderListEntity.setIsfirst("0");
          }
            resultOrderList.add(cpsOrderListEntity);
        }
        return  resultOrderList;
    }

    public  Boolean  saveCpsOrderList(List<CpsOrderList>  cpsOrderListCollection) {
        logger.info("执行saveCpsOrderList、、、、、、、、、、、、begin");
        Boolean bl = false;
        if (CollectionUtils.isEmpty(cpsOrderListCollection)) {
            logger.info("开始保存cpsOrderListCollection、、、、、、、、、 cpsOrderListCollection  is  null ");
            bl = false;
            return bl;
        }
        try{
            logger.info("开始保存cpsOrderListCollection");
            Map params = new HashMap();
            params.put("orderList", cpsOrderListCollection);
            int effectLine = cpsOrderListMapper.addCpsOrderListBatch(params);
            bl = true;
        }catch (Exception e1){
            //遍历重复的订单号
            logger.info("保存cpsOrderList、、、、、、、、、、、出现异常，开始去除已经存在的订单");
            for(CpsOrderList cpsOrderListEntity:cpsOrderListCollection){
                CpsOrderList  cpsOrderListExist = cpsOrderListMapper.getCpsOrderListByOrderId(cpsOrderListEntity.getOrderid());
                if(null!=cpsOrderListExist){
                    logger.info("订单号："+cpsOrderListEntity.getOrderid()+"已经存在,正在移除、、、、、、");
                    cpsOrderListCollection.remove(cpsOrderListEntity);
                }
            }
            try{
                logger.info("再次开始保存cpsOrderListCollection");
                if (CollectionUtils.isEmpty(cpsOrderListCollection)) {
                    return bl;
                }
                Map params = new HashMap();
                params.put("orderList", cpsOrderListCollection);
                int effectLine = cpsOrderListMapper.addCpsOrderListBatch(params);
                logger.info("再次开始保存cpsOrderListCollection成功");
                bl = true;
            }catch (Exception e2) {
                logger.info("再次开始保存cpsOrderListCollection失败,失败原因："+e2.getMessage());
                bl = false;
            }
        }finally {
            logger.info("执行saveCpsOrderList.....................end");
            return bl;
        }
    }

    @Transactional("cps")
    public void saveOrderSummary(){
        //查找对应orderList数据
        String isNew = ValidEnum.VALID.getCode();
        List<String>  distinctChannelCodeList = cpsOrderMapper.getIsNewDistinctChannelCode(isNew);
        if(CollectionUtils.isEmpty(distinctChannelCodeList)){
            return;
        }
        for(String channelCode : distinctChannelCodeList){
            try {
                saveOrderSummaryByChannelCode(channelCode);
            } catch (CpsException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOrderSummaryByChannelCode(String channelCode) throws CpsException {
        CpsChannel cpsChannel = getCpsChannelByCode(channelCode);
        if(cpsChannel == null){
            throw new CpsException(String.format("find cpsChannel result is null,params channelCode[%s]", channelCode));
        }
        List<CpsRateConfig> cpsRateConfigList = getCpsRateConfig(cpsChannel.getId());
        for(CpsRateConfig cpsRateConfig : cpsRateConfigList){
            CpsOrderSummaryCollect cpsOrderSummaryCollect = null;
            try {
                cpsOrderSummaryCollect = buildCpsOrderSummaryCollect(cpsChannel, cpsRateConfig);
            }catch (Exception e){
                logger.error(String.format("buildCpsOrderSummaryCollect error,params cpsChannel[%s] cpsRateConfig[%s]", cpsChannel, cpsRateConfig), e);
            }
            if(cpsOrderSummaryCollect == null){
                continue;
            }
            //保存对应的cpsOrderSummaryCollect
            cpsOrderSummaryCollectMapper.insert(cpsOrderSummaryCollect);
            //cpsOrderList回填cpsOrderSummaryCollectId
            cpsOrderMapper.updateSummaryDetailId(channelCode, cpsOrderSummaryCollect.getId());
        }
    }

    private CpsOrderSummaryCollect buildCpsOrderSummaryCollect(CpsChannel cpsChannel, CpsRateConfig cpsRateConfig) {
        CpsOrderSummaryCollect cpsOrderSummaryCollect = null;
        Date payStartDate = cpsRateConfig.getPaystartdate();
        Date payEndDate = cpsRateConfig.getPayenddate();
        payEndDate = DateUtils.add(payEndDate, Calendar.DATE, 1);
        if(payStartDate == null || payEndDate == null){
            logger.error(String.format("cpsRateConfig payStartDate or payEndDate is null,params cpsRateConfig Id[%s]", cpsRateConfig.getId()));
            return null;
        }else{
            cpsOrderSummaryCollect = new CpsOrderSummaryCollect();
        }
        //查询对出对应的订单数据
        boolean isFirst = true;
        CpsOrderListSummary cpsOrderListSummaryIsFirst = cpsOrderMapper.getCpsOrderListSummary(isFirst, payStartDate, payEndDate, cpsChannel.getChannelcode());
        if(cpsOrderListSummaryIsFirst.getSumOrder() == null){
            cpsOrderListSummaryIsFirst.setSumOrder(0);
        }
        if(cpsOrderListSummaryIsFirst.getSumOrderPrice() == null){
            cpsOrderListSummaryIsFirst.setSumOrderPrice(new BigDecimal("0"));
        }
        isFirst = false;
        CpsOrderListSummary cpsOrderListSummaryNoFirst = cpsOrderMapper.getCpsOrderListSummary(isFirst, payStartDate, payEndDate, cpsChannel.getChannelcode());
        if(cpsOrderListSummaryNoFirst.getSumOrder() == null){
            cpsOrderListSummaryNoFirst.setSumOrder(0);
        }
        if(cpsOrderListSummaryNoFirst.getSumOrderPrice() == null){
            cpsOrderListSummaryNoFirst.setSumOrderPrice(new BigDecimal("0"));
        }
        if(cpsOrderListSummaryIsFirst.getUpdateTime() != null){
            cpsOrderSummaryCollect.setOrderdate(cpsOrderListSummaryIsFirst.getUpdateTime());
        }else{
            cpsOrderSummaryCollect.setOrderdate(cpsOrderListSummaryNoFirst.getUpdateTime());
        }

        cpsOrderSummaryCollect.setCreatetime(new Date());
        cpsOrderSummaryCollect.setChannelcode(cpsChannel.getChannelcode());
        cpsOrderSummaryCollect.setChannelname(cpsChannel.getChannelname());
        cpsOrderSummaryCollect.setChanneltype(cpsChannel.getTypeid());
        //订单金额信息
        cpsOrderSummaryCollect.setSumorderfirst(cpsOrderListSummaryIsFirst.getSumOrder());
        cpsOrderSummaryCollect.setSumorderpricefirst(cpsOrderListSummaryIsFirst.getSumOrderPrice());

        cpsOrderSummaryCollect.setSumorder(cpsOrderListSummaryNoFirst.getSumOrder());
        cpsOrderSummaryCollect.setSumorderprice(cpsOrderListSummaryNoFirst.getSumOrderPrice());

        cpsOrderSummaryCollect.setCpsrate(cpsRateConfig.getCpsrate().doubleValue());
        cpsOrderSummaryCollect.setCpsratefirst(cpsRateConfig.getFirstcpsrate().doubleValue());

        cpsOrderSummaryCollect.setDeductrate(cpsRateConfig.getDeductrate().doubleValue());
        cpsOrderSummaryCollect.setDeductratefirst(cpsRateConfig.getFirstdeductrate().doubleValue());

        cpsOrderSummaryCollect.setSumorderpricerate(cpsRateConfig.getCpsrate().multiply(new BigDecimal(cpsOrderListSummaryNoFirst.getSumOrder())));
        cpsOrderSummaryCollect.setSumorderpriceratefirst(cpsRateConfig.getFirstcpsrate().multiply(new BigDecimal(cpsOrderListSummaryIsFirst.getSumOrder())));

        cpsOrderSummaryCollect.setRuleupdatetime(cpsRateConfig.getUpdatetime());
        cpsOrderSummaryCollect.setRuleupdateby(cpsRateConfig.getUpdateby());
        //cps金额=     sum结算订单（眯客价（实际支付金额）*cps比例） *（1-扣率）
        BigDecimal firstCps = cpsOrderListSummaryIsFirst.getSumOrderPrice().multiply(
                cpsRateConfig.getFirstcpsrate()).multiply(new BigDecimal("1").subtract(cpsRateConfig.getFirstdeductrate()));

        BigDecimal noFirstCps = cpsOrderListSummaryNoFirst.getSumOrderPrice().multiply(
                cpsRateConfig.getCpsrate()).multiply(new BigDecimal("1").subtract(cpsRateConfig.getDeductrate()));
        BigDecimal totalCps = firstCps.add(noFirstCps);
        cpsOrderSummaryCollect.setTotalprice(totalCps);
        return cpsOrderSummaryCollect;
    }

    public List<CpsOrderList> getCpsOrderList(String channelCode, String isNew) {
        CpsOrderListExample example = new CpsOrderListExample();
        example.createCriteria().andChannelcodeEqualTo(channelCode).andIsnewEqualTo(isNew);
        return cpsOrderMapper.selectByExample(example);
    }

    public CpsChannel getCpsChannelByCode(String channelCode) throws CpsException {
        CpsChannel cpsChannel = null;
        CpsChannelExample cpsChannelExample = new CpsChannelExample();
        cpsChannelExample.createCriteria().andChannelcodeEqualTo(channelCode);
        List<CpsChannel> cpsChannelList = cpsChannelMapper.selectByExample(cpsChannelExample);
        if(CollectionUtils.isEmpty(cpsChannelList)){
            return null;
        }
        if(cpsChannelList.size() > 1){
            throw new CpsException(String.format("find CpsChannel by channelCode result size > 1,params channelCode[%s]", channelCode));
        }
        cpsChannel = cpsChannelList.get(0);
        return cpsChannel;
    }

    public List<CpsRateConfig> getCpsRateConfig(Integer channelId) {
        CpsRateConfigExample example = new CpsRateConfigExample();
        example.createCriteria().andChannelidEqualTo(channelId);
        example.setOrderByClause("id");
        List<CpsRateConfig> cpsRateConfigList = cpsRateConfigMapper.selectByExample(example);
        return cpsRateConfigList;
    }

}
