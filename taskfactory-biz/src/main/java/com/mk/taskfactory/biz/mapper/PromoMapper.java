package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.BPromo;

@MyBatisRepository
public interface PromoMapper {
    public Integer checkBPromoByPwd(String pwd);
    public BPromo insert(BPromo pwd);
}
