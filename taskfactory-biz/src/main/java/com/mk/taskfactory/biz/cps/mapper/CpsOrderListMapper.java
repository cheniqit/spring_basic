package com.mk.taskfactory.biz.cps.mapper;

import com.mk.taskfactory.biz.cps.bean.CpsOrderListSummary;
import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsOrderListExample;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface CpsOrderListMapper {
    int countByExample(CpsOrderListExample example);

    int insert(CpsOrderList record);

    int insertSelective(CpsOrderList record);

    List<CpsOrderList> selectByExample(CpsOrderListExample example);

    int  addCpsOrderListBatch(Map params);

    List<String> getIsNewDistinctChannelCode(String isNew);

    public Date getMaxCheckOutTime();

    int updateSummaryDetailId(@Param("channelCode")String channelCode, @Param("cpsOrderSummaryCollectId")Long cpsOrderSummaryCollectId);

    public   CpsOrderList  getCpsOrderListByOrderId(Long  orderId);

    CpsOrderListSummary getCpsOrderListSummary(@Param("isFirst")boolean isFirst, @Param("payStartDate")Date payStartDate,
                                               @Param("payEndDate")Date payEndDate, @Param("channelCode")String channelCode);
}