package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.BCard;

@MyBatisRepository
public interface CardMapper {
    public Integer checkBPromoByPwd(String pwd);
    public Integer insert(BCard bean);
}
