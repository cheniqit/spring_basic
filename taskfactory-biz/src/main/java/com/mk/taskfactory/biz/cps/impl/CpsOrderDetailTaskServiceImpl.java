package com.mk.taskfactory.biz.cps.impl;

import com.mk.taskfactory.api.cps.CpsOrderDetailTaskService;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.cps.bean.CpsOrderListSummary;
import com.mk.taskfactory.biz.cps.model.*;
import com.mk.taskfactory.biz.mapper.OtaOrderMapper;
import com.mk.taskfactory.biz.mapper.UMemberMapper;
import com.mk.taskfactory.biz.mapper.cps.CpsChannelMapper;
import com.mk.taskfactory.biz.mapper.cps.CpsOrderListMapper;
import com.mk.taskfactory.biz.mapper.cps.CpsOrderSummaryCollectMapper;
import com.mk.taskfactory.biz.mapper.cps.CpsRateConfigMapper;
import com.mk.taskfactory.biz.order.impl.OtaOrderServiceImpl;
import com.mk.taskfactory.biz.order.model.OtaOrder;
import com.mk.taskfactory.biz.umember.model.UMember;
import com.mk.taskfactory.common.exception.CpsException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    private CpsOrderListMapper cpsOrderListMapper;

    @Autowired
    private OtaOrderServiceImpl otaOrderServiceImpl;

    @Autowired
    private CpsRateConfigMapper cpsRateConfigMapper;

    @Autowired
    private CpsOrderSummaryCollectMapper cpsOrderSummaryCollectMapper;

    @Autowired
    private OtaOrderMapper otaOrderMapper;


    public  void    cpsOrderProduce(){
        logger.info(" begin cpsOrderProduce []");
        CpsChannelExample cpsChannelExample = new CpsChannelExample();
        cpsChannelExample.createCriteria().andValidEqualTo("F");
        List<CpsChannel>  cpsChannelList = cpsChannelMapper.selectByExample(cpsChannelExample);
        if(CollectionUtils.isEmpty(cpsChannelList)){
            logger.info("query  cpsChannel : [cpsChannelList = null]  " );
            return ;
        }
        for(CpsChannel cpsChannle:cpsChannelList){
            HashMap<Long,UMember> memberMap = new  HashMap<Long,UMember>();
            String  channelCode = cpsChannle.getChannelcode();
            if(StringUtils.isEmpty(channelCode)){
                continue;
            }
            HashMap  hmap = new HashMap();
            hmap.put("comefrom", channelCode);
            List<UMember>  umemberList =   umemberMapper.selectCpsUserByComeFrom(hmap);
            if(CollectionUtils.isEmpty(cpsChannelList)){
                continue ;
            }
            String mids = "";
            for(UMember umember:umemberList){
                umember.setCpsName(cpsChannle.getChannelname());
                memberMap.put(umember.getMid(), umember);
                mids = mids  + "," + umember.getMid();
            }
            if(mids.length()>0&&mids.startsWith(",")){
                mids = mids.substring(1,mids.length());
            }
        }
    }

    public   List<OtaOrder>  queryOrderByMid(String  mids,Date maxTime){
        List<OtaOrder>  resultOrderList = new ArrayList<OtaOrder>();
        HashMap map = new HashMap();
        map.put("mids",mids);
        map.put("maxTime", maxTime);
        List<OtaOrder>  orderList =  otaOrderMapper.selectByMid(map);
        if(CollectionUtils.isEmpty(orderList)){
            return null ;
        }
        for(OtaOrder  otaOrder:orderList){
            if(otaOrderServiceImpl.isFirstOrder(otaOrder)){
                resultOrderList.add(otaOrder);
            }
        }
        return null;
    }

    public  Boolean  saveCpsOrderList(List<CpsOrderList>  cpsOrderList) {
        if (CollectionUtils.isEmpty(cpsOrderList)) {
            return false;
        }
        int effectLine = cpsOrderListMapper.addCpsOrderListBatch(cpsOrderList);
        return true;
    }

    public void saveOrderSummary(){
        //查找对应orderList数据
        String isNew = ValidEnum.VALID.getCode();
        List<String>  distinctChannelCodeList = cpsOrderMapper.getIsNewDistinctChannelCode(isNew);
        if(CollectionUtils.isNotEmpty(distinctChannelCodeList)){
            return;
        }
        for(String channelCode : distinctChannelCodeList){
            saveOrderSummaryByChannelCode(channelCode);
        }
    }

    public void saveOrderSummaryByChannelCode(String channelCode){
        CpsChannel cpsChannel = getCpsChannel(channelCode);
        if(cpsChannel == null){
            throw new CpsException(String.format("find cpsChannel result is null,params channelCode[%s]", channelCode));
        }
        List<CpsRateConfig> cpsRateConfigList = getCpsRateConfig(cpsChannel.getId());
        for(CpsRateConfig cpsRateConfig : cpsRateConfigList){
            CpsOrderSummaryCollect cpsOrderSummaryCollect = buildCpsOrderSummaryCollect(cpsChannel, cpsRateConfig);
            if(cpsOrderSummaryCollect == null){
                continue;
            }
            //保存对应的cpsOrderSummaryCollect
            cpsOrderSummaryCollectMapper.insert(cpsOrderSummaryCollect);
            //cpsOrderList回填cpsOrderSummaryCollectId
            cpsOrderMapper.updateSummaryDetailId(cpsOrderSummaryCollect.getId());
        }
    }

    private CpsOrderSummaryCollect buildCpsOrderSummaryCollect(CpsChannel cpsChannel, CpsRateConfig cpsRateConfig) {
        CpsOrderSummaryCollect cpsOrderSummaryCollect = null;
        Date payStartDate = cpsRateConfig.getPaystartdate();
        Date payEndDate = cpsRateConfig.getPayenddate();
        if(payStartDate == null || payEndDate == null){
            logger.error(String.format("cpsRateConfig payStartDate or payEndDate is null,params cpsRateConfig Id[%s]", cpsRateConfig.getId()));
            return null;
        }
        //查询对出对应的订单数据
        boolean isFirst = true;
        CpsOrderListSummary cpsOrderListSummaryIsFirst = cpsOrderMapper.getCpsOrderListSummary(isFirst, payStartDate, payEndDate, cpsChannel.getChannelcode());
        isFirst = false;
        CpsOrderListSummary cpsOrderListSummaryNoFirst = cpsOrderMapper.getCpsOrderListSummary(isFirst, payStartDate, payEndDate, cpsChannel.getChannelcode());

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
                cpsRateConfig.getCpsrate()).multiply(new BigDecimal("1").subtract(cpsRateConfig.getFirstdeductrate()));

        return cpsOrderSummaryCollect;
    }

    public List<CpsOrderList> getCpsOrderList(String channelCode, String isNew) {
        CpsOrderListExample example = new CpsOrderListExample();
        example.createCriteria().andChannelcodeEqualTo(channelCode).andIsnewEqualTo(isNew);
        return cpsOrderMapper.selectByExample(example);
    }

    public CpsChannel getCpsChannel(String channelCode) {
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
        List<CpsRateConfig> cpsRateConfigList = cpsRateConfigMapper.selectByExample(example);
        return cpsRateConfigList;
    }
}
