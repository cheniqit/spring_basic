package com.mk.taskfactory.biz.impl.ots;

import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.biz.impl.ValidRateTaskLogicServiceImpl;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.THotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Thinkpad on 2015/10/17.
 */
@Service
public class HotelRemoteService {
    private static Logger logger = LoggerFactory.getLogger(ValidRateTaskLogicServiceImpl.class);

    private final static String UPDATE_MIKE_PRICE_CACHE = "/hotel/updatemikepricecache";

    @Autowired
    private HotelMapper hotelMapper;

    public boolean updateMikePriceCache(String hotelid){
        logger.info(String.format("remote url [%s] begin params hotelid[%s]", this.UPDATE_MIKE_PRICE_CACHE, hotelid));
        HashMap params = new HashMap();
        params.put("hotelid", hotelid);

        boolean successFlag = true;
        try {
            String jsonStr = HttpUtils.doPost(Constants.OTS_URL + this.UPDATE_MIKE_PRICE_CACHE, params);

            logger.info(String.format("remote url [%s] end params result[%s]", this.UPDATE_MIKE_PRICE_CACHE, jsonStr));
            JSONObject jsonObject = JsonUtils.parseObject(jsonStr);
            if(jsonObject.getBoolean("success") == null){
                successFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return successFlag;
    }

    public String hotelInit(String token, String cityId, String hotelId){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", token);
        params.put("cityid", cityId);
        params.put("hotelId", hotelId);
        String postResult=HttpUtils.doPost(Constants.OTS_URL + "/hotel/init", params);
        return postResult;
    }
    /******单个刷索引*******/
    public String hotelInit(String hotelId){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", Constants.token);
        params.put("hotelId", hotelId);
        String postResult=HttpUtils.doPost(Constants.OTS_URL + "/indexerjob/addhotelids", params);
        return postResult;
    }
    /******全量刷索引*******/
    public String indexerjob(){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", Constants.token);
        String postResult= HttpUtils.doPost(Constants.OTS_URL + "/indexerjob/addallhotel", params);
        return postResult;
    }
    /******单个更新索引*******/
    public String indexerDrop(String hotelId){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", Constants.token);
        params.put("hoteld", hotelId);
        String postResult= HttpUtils.doPost(Constants.OTS_URL + "/indexer/drop", params);
        return postResult;
    }
    /******刷新城市列表*******/
    public String initcity(){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", Constants.token);
        String postResult= HttpUtils.doPost(Constants.OTS_URL + "/indexer/initcity", params);
        return postResult;
    }
    public String updatemikeprices(String token, String hotelId){
        Map<String, String> params=new HashMap<String, String>();
        params.put("token", token);
        params.put("hotelId", hotelId);
        String postResult=HttpUtils.doPost(Constants.OTS_URL + "/hotel/updatemikeprices", params);
        return postResult;
    }

    public void initHotel(Set<Integer> hotelSet) {
        logger.info(String.format("====================initHotel >> initHotel start"));
        if (null == hotelSet || hotelSet.isEmpty()) {
            logger.info(String.format("====================initHotel >> initHotel set is null end"));
            return;
        }

        Integer leftSize = hotelSet.size();
        logger.info(String.format("====================initHotel >> hotelSet.size:[%s]", leftSize));

        //updateMikePriceCache
        for (Integer hotelId : hotelSet) {
            logger.info(String.format("====================initHotel >> updateMikePriceCache left:[%s]", leftSize--));

            //updateMikePriceCache
            logger.info(String.format("====================initHotel >> updateMikePriceCache hotel id[%s] start", hotelId));

            boolean updateCacheSuccessFlag = this.updateMikePriceCache(hotelId.toString());
            if (!updateCacheSuccessFlag) {
                logger.info(String.format("====================initHotel>> updateMikePriceCache faild hotel id : [%s] try again", hotelId));
                updateCacheSuccessFlag = this.updateMikePriceCache(hotelId.toString());

                if (!updateCacheSuccessFlag) {
                    logger.info(String.format("====================initHotel>> updateMikePriceCache faild hotel id : [%s]", hotelId));
                }
            }
            logger.info(String.format("====================initHotel >> updateMikePriceCache hotel id[%s] end", hotelId));

        }

        //initHotel
        leftSize = hotelSet.size();
        for (Integer hotelId : hotelSet) {
            logger.info(String.format("====================initHotel >> hotelInit left:[%s]", leftSize--));
            THotel hotel= hotelMapper.getCityIdByHotelId(Integer.valueOf(hotelId));
            if (hotel==null){
                logger.info(String.format("====================initHotel>>find hotel hotelId[%s] not find continue", hotelId));
                continue;
            }
            logger.info(String.format("====================initHotel >> find hotel id[%s] end", hotelId));
            //hotelInit
            logger.info(String.format("====================initHotel >> hotelInit hotel id[%s] start", hotelId));
            try {
                this.hotelInit(Constants.token, hotel.getCityId().toString(), hotel.getId().toString());
            }catch (Exception e) {
                logger.info(String.format("====================initHotel >> hotelInit hotel id[%s] faild try again", hotelId));
                try {
                    this.hotelInit(Constants.token, hotel.getCityId().toString(), hotel.getId().toString());
                }catch (Exception e1) {
                    logger.info(String.format("====================initHotel >> hotelInit hotel id[%s] faild", hotelId));
                }
            }

            logger.info(String.format("====================initHotel >> hotelInit hotel id[%s] end", hotelId));
        }

        //
        logger.info(String.format("====================initHotel >> sleep 5000 start"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        logger.info(String.format("====================initHotel >> sleep 5000 end"));

        //
        leftSize = hotelSet.size();
        for (Integer hotelId : hotelSet) {
            logger.info(String.format("====================initHotel >> updatemikeprices left:[%s]", leftSize--));
            //updatemikeprices
            logger.info(String.format("====================initHotel >> updatemikeprices hotel id[%s] start", hotelId));
            try {
                this.updatemikeprices(Constants.token, hotelId.toString());
            } catch (Exception e) {
                logger.info(String.format("====================initHotel >> updatemikeprices hotel id[%s] faild try again", hotelId));
                try {
                    this.updatemikeprices(Constants.token, hotelId.toString());
                } catch (Exception e1) {
                    logger.info(String.format("====================initHotel >> updatemikeprices hotel id[%s] faild", hotelId));
                }
            }
            logger.info(String.format("====================initHotel >> updatemikeprices hotel id[%s] end", hotelId));
        }

        logger.info(String.format("====================initHotel >> initHotel end"));
    }
}
