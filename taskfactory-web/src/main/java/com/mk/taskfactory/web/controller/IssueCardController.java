package com.mk.taskfactory.web.controller;


import com.dianping.cat.Cat;
import com.mk.taskfactory.api.PromoCardService;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/issuecard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class IssueCardController {
    @Autowired
    private PromoCardService promoCardService;
    @RequestMapping(value = "/createPromoCard", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPromoCard() {
        Cat.logEvent("createPromoCard ", "发布优惠劵" + DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        promoCardService.createPromoCard();
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/createPrepaidCard", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPrepaidCard() {
        Cat.logEvent("createPrepaidCard ", "发布充值卡" + DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        promoCardService.createPrepaidCard();
        return new ResponseEntity( HttpStatus.OK);
    }
}
