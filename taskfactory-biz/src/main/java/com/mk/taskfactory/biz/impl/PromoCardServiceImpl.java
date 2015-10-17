package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.PromoCardService;
import com.mk.taskfactory.biz.mapper.CardMapper;
import com.mk.taskfactory.biz.mapper.PromoCardTypeMapper;
import com.mk.taskfactory.biz.mapper.PromoMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.model.BCard;
import com.mk.taskfactory.model.BPromo;
import com.mk.taskfactory.model.BPromoCardType;
import com.mk.taskfactory.api.dtos.ValueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class PromoCardServiceImpl implements PromoCardService {
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private PromoMapper promoMapper;
    @Autowired
    private PromoCardTypeMapper promoCardTypeMapper;
    private static String genRandomPwdCode(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWSYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public String getCardNo(Double price,Integer count) {
        String cardNo="";
        if (price==null){
            cardNo+="MKYH";
        }else{
            cardNo+=price.intValue()+"MKCZ";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date= sdf.format(new Date());
        cardNo+=date;
        String counts=count.toString();
        for (int i=0;i<5-counts.length();i++){
            counts+="0"+counts;
        }
        cardNo+=counts;
        return  cardNo;
    }
    //1优惠劵，2充值卡
    public void createPromoCard(){
        BPromoCardType promoCardType= promoCardTypeMapper.findBPromoCardTypeByType(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date= sdf.format(new Date());
        String batchNo=date+"01";
        if (promoCardType==null||promoCardType.getNum()==null){
            return;
        }
        for (int i=1;i<=promoCardType.getNum();i++){
            String pwd=getPromoByPwd();
            String cardNo=getCardNo(null,i);
            BPromo promo=new BPromo();
            promo.setBatchNo(batchNo);
            promo.setPromoName(promoCardType.getCardName());
            promo.setPromoNo(cardNo);
            promo.setPromoPwd(pwd);
            promo.setPromoStatus(ValueTypeEnum.TYPE_ACTIVE.getId());
            promo.setPromoCityId(promoCardType.getUseCityId());
            promo.setPromoType(1);
            promo.setBeginTime(promoCardType.getBeginUseTime());
            promo.setEndTime(promoCardType.getEndUseTime());
            promo.setBeginDate(promoCardType.getBeginUseDate());
            promo.setEndDate(promoCardType.getEndUseDate());
            promo.setDescription(promoCardType.getDescription());
            promo.setCreateTime(DateUtils.format_yMdHms(new Date()));
            promoMapper.insert(promo);
        }


    }
    public String getPromoByPwd(){
        String pwd=genRandomPwdCode(8);
        Integer promo=promoMapper.checkBPromoByPwd(pwd);
        if (promo!=null){
            getPromoByPwd();
        }
        return pwd;
    }
    public void createPrepaidCard(){
        BPromoCardType promoCardType= promoCardTypeMapper.findBPromoCardTypeByType(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date= sdf.format(new Date());
        String batchNo=date+"02";
        if (promoCardType==null||promoCardType.getNum()==null){
            return;
        }
        for (int i=1;i<=promoCardType.getNum();i++){
            String pwd=getPrepaidByPwd();
            String cardNo=getCardNo(promoCardType.getPrice(), i);
            BCard card=new BCard();
            card.setBatchNo(batchNo);
            card.setName(promoCardType.getCardName());
            card.setNo(cardNo);
            card.setPassword(pwd);
            card.setStatus(ValueTypeEnum.TYPE_ACTIVE.getId());
            card.setPrice(promoCardType.getPrice());
            card.setCost(promoCardType.getCost());
            card.setBeginDate(promoCardType.getBeginUseDate());
            card.setEndDate(promoCardType.getEndUseDate());
            card.setDescription(promoCardType.getDescription());
            card.setCreateTime(DateUtils.format_yMdHms(new Date()));
            cardMapper.insert(card);
        }


    }
    public String getPrepaidByPwd(){
        String pwd=genRandomPwdCode(10);
        Integer promo=cardMapper.checkBPromoByPwd(pwd);
        if (promo!=null){
            getPrepaidByPwd();
        }
        return pwd;
    }



}
