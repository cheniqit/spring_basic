package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.BPromo;

@MyBatisRepository
public interface PromoMapper {
    public Integer checkBPromoByPwd(String pwd);
    public Integer insert(BPromo bean);
}
