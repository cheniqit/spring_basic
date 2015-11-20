package com.mk.taskfactory.biz.mapper.cps;

import com.mk.taskfactory.biz.cps.bean.CpsOrderListSummary;
import com.mk.taskfactory.biz.cps.model.CpsOrderList;
import com.mk.taskfactory.biz.cps.model.CpsOrderListExample;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisRepository
public interface CpsOrderListMapper {
    int countByExample(CpsOrderListExample example);

    int insert(CpsOrderList record);

    int insertSelective(CpsOrderList record);

    List<CpsOrderList> selectByExample(CpsOrderListExample example);

    List<String> getIsNewDistinctChannelCode(String isNew);

    public Date getMaxCheckOutTime();

    public int addCpsOrderListBatch(List<CpsOrderList>  cpsOrderList);

    int updateSummaryDetailId(@Param("cpsOrderSummaryCollectId'")Long cpsOrderSummaryCollectId);

    CpsOrderListSummary getCpsOrderListSummary(@Param("isFirst")boolean isFirst, @Param("payStartDate")Date payStartDate,
                                               @Param("payEndDate")Date payEndDate, @Param("channelCode")String channelCode);
}