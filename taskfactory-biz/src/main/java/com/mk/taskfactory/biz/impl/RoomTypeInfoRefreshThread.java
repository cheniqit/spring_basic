package com.mk.taskfactory.biz.impl;

import com.mk.framework.AppUtils;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.dtos.crawer.RoomtypeToRedisDto;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelRoomTypeDto;
import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeDto;
import com.mk.taskfactory.api.enums.HotelSourceEnum;
import com.mk.taskfactory.api.enums.RoomTypePriceTypeEnum;
import com.mk.taskfactory.api.ht.QHotelRoomTypeService;
import com.mk.taskfactory.biz.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kirinli on 16/4/12.
 */
@Service
public class RoomTypeInfoRefreshThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(QHotelToRedisServiceImpl.class);

    private OnlineHotelRoomTypeDto roomTypeDto;

    RoomTypeInfoRefreshThread(OnlineHotelRoomTypeDto roomTypeDto){
        this.roomTypeDto = roomTypeDto;
    }

    @Override
    public void run() {
        Jedis jedis = null;
        QHotelRoomTypeService hotelRoomTypeService = AppUtils.getBean(QHotelRoomTypeService.class);
        try {
            jedis =  RedisUtil.getJedis();
            logger.info(String.format("\n====================id={}&hotelId={}&roomTypeId={}====================\n")
                    ,roomTypeDto.getId(),roomTypeDto.getHotelId(),roomTypeDto.getRoomTypeId());
            if (roomTypeDto.getHotelId()!=null) {
                String hotelSource = jedis.get(String.format("%s%s", RedisCacheName.HOTEL_SOURCE, roomTypeDto.getHotelId().toString()));
                if (StringUtils.isEmpty(hotelSource)) {
                    logger.info(String.format("\n====================hotelSource is Empty====================\n"));
                    return;
                }
                Map<String, String> priceMap = new HashMap<String, String>();
                priceMap.put("price", roomTypeDto.getPrice().toString());
                if ("T".equals(roomTypeDto.getIsOtaPrice())) {
                    priceMap.put("type", RoomTypePriceTypeEnum.OTA.getCode().toString());

                } else {
                    priceMap.put("type", RoomTypePriceTypeEnum.DYNAMIC.getCode().toString());
                }
                if("T".equals(roomTypeDto.getIsVaild())) {
                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_DYNAMIC_PRICE,
                            roomTypeDto.getRoomTypeId()), JsonUtils.toJSONString(priceMap)
                    );
                }else {
                    jedis.del(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_DYNAMIC_PRICE,
                            roomTypeDto.getRoomTypeId())
                    );
                }

                if (HotelSourceEnum.OTA.getCode()==Integer.valueOf(hotelSource)) {
                    Set<String> hotelRoomTypeSet = jedis.smembers(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                            roomTypeDto.getHotelId()));
                    for (String roomType: hotelRoomTypeSet){
                        RoomtypeToRedisDto roomtypeToRedisDto = JsonUtils.formatJson(roomType,RoomtypeToRedisDto.class);
                        if (roomtypeToRedisDto==null||roomtypeToRedisDto.getRoomTypeId()==null){
                            continue;
                        }
                        if (roomtypeToRedisDto.getRoomTypeId().equals(roomTypeDto.getRoomTypeId())){
                            jedis.srem(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                    roomTypeDto.getHotelId()), roomType
                            );
                        }
                    }

                    QHotelRoomtypeDto qHotelRoomtype = new QHotelRoomtypeDto();
                    qHotelRoomtype.setRoomTypeId(roomTypeDto.getRoomTypeId());
                    qHotelRoomtype = hotelRoomTypeService.getByPramas(qHotelRoomtype);
                    if (qHotelRoomtype == null || qHotelRoomtype.getId() == null) {
                        logger.info(String.format("\n====================qHotelRoomtype isEmpty====================\n"));
                        return;
                    }
                    RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
//                                    QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
//                                    TExHotelImageDto hotelImageDto = new TExHotelImageDto();
//                                    String picId = jedis.get(String.format("%s%s", RedisCacheName.roomType_pic_mapping, qHotelRoomtype.getRoomTypeId()));
//                                    if (StringUtils.isEmpty(picId)) {
//                                        getImgBean.setHotelSourceId(qHotelRoomtype.getHotelSourceId());
//                                        getImgBean.setRoomtypeKey(qHotelRoomtype.getRoomtypeKey());
//                                        Integer imgId = roomTypeImgMapper.getRoomtypeImg(getImgBean);
//                                        if (imgId == null) {
//                                            hotelImageDto.setHotelSourceId(qHotelRoomtype.getHotelSourceId());
//                                            hotelImageDto.setTag("客房");
//                                            List<TExHotelImageDto> hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
//                                            if (!CollectionUtils.isEmpty(hotelImageDtoList)) {
//                                                hotelImageDto = hotelImageDtoList.get(0);
//                                            }
//                                        } else {
//                                            hotelImageDto.setId(getImgBean.getId());
//                                            hotelImageDto = otsHotelImageService.getByPramas(hotelImageDto);
//                                        }
//                                    } else {
//                                        hotelImageDto.setId(new Long(picId));
//                                        hotelImageDto = otsHotelImageService.getByPramas(hotelImageDto);
//                                    }
                    BeanUtils.copyProperties(qHotelRoomtype, bean);
                    bean.setImageUrl(qHotelRoomtype.getRoomPic());
                    if("T".equals(roomTypeDto.getIsVaild())) {
                        jedis.sadd(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                roomTypeDto.getHotelId()), JsonUtils.toJSONString(bean)
                        );
                        jedis.set(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
                                roomTypeDto.getRoomTypeId()), JsonUtils.toJSONString(bean)
                        );
                    }else{
                        jedis.del(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
                                roomTypeDto.getRoomTypeId())
                        );
                    }

                }else {
                    if ("T".equals(roomTypeDto.getIsVaild())) {
                        jedis.set(String.format("%s%s", RedisCacheName.LEZHU_ONLINE_ROOMTYPE,
                                roomTypeDto.getRoomTypeId()), "1"
                        );
                    } else {
                        jedis.del(String.format("%s%s", RedisCacheName.LEZHU_ONLINE_ROOMTYPE,
                                roomTypeDto.getRoomTypeId())
                        );
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }

        }

    }
}
