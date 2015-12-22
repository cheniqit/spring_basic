package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.HotelPicSyncService;
import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.api.dtos.EHotelPicResDto;
import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.TRoomTypeInfoDto;
import com.mk.taskfactory.api.enums.HMSStatusEnum;
import com.mk.taskfactory.api.enums.HotelPicEnum;
import com.mk.taskfactory.biz.mapper.ots.*;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.model.PicJsonCalss;
import com.mk.taskfactory.model.THotel;
import com.mk.taskfactory.model.TRoomType;
import com.mk.taskfactory.model.TRoomTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class HotelPicSyncServiceImpl implements HotelPicSyncService {
    private static Logger logger = LoggerFactory.getLogger(HotelPicSyncServiceImpl.class);
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomTypeInfoMapper roomTypeInfoMapper;
    @Autowired
    private HotelPicMapper hotelPicMapper;
    @Autowired
    private HotelPicResMapper hotelPicResMapper;
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    public void hotelPicSync() {
        THotelDto hotelDto=new THotelDto();
        Integer count=hotelMapper.countTHotel(hotelDto);
        if (count<=0){
            return;
        }
        logger.info(String.format("====================init PMS hotelPic sync job >> hotelPicSync method begin===================="));
        Integer pageSize=100;//100
        Integer pageNum=count/pageSize;
        logger.info(String.format("====================init PMS hotelPic sync job >> hotelPicSync hotelSize"+count+"===================="));
        for (int i=0;i<=pageNum;i++){
            hotelDto.setPageIndex(i);
            hotelDto.setPageSize(pageSize);
            List<THotel>hotels=hotelMapper.queryTHotel(hotelDto);
            logger.info(String.format("====================init PMS hotelPic sync job >> hotelPicSync setPageIndex"+i+"===================="));
            if (CollectionUtils.isEmpty(hotels)){
                continue;
            }
            for (THotel hotel:hotels){
                if (StringUtils.isEmpty(hotel.getHotelPic())){
                    continue;
                }
                logger.info(String.format("====================init PMS hotelPic sync job >> hotelPicSync hotelId"+hotel.getId()+"===================="));
                List<PicJsonCalss> picJsonCalsses=getPicUrl(hotel.getHotelPic());

                for (PicJsonCalss picJsonCalss:picJsonCalsses){
                    if (picJsonCalss.getName()==null||picJsonCalss.getPic()==null){
                        continue;
                    }
                    String typeCode=HotelPicEnum.getByCode(picJsonCalss.getName(),1).getId();
                    for (String picUrl:picJsonCalss.getPic()){
                        EHotelPicResDto hotelPicResDto =new EHotelPicResDto();
                        hotelPicResDto.setTypeCode(typeCode);
                        hotelPicResDto.setUrl(picUrl);
                        hotelPicResDto.setStateCode(HMSStatusEnum.PASS.getCode());
                        hotelPicResDto.setHotelId(hotel.getId().longValue());
                        Long resId=saveHotelPicRes(hotelPicResDto);
                        if (resId==null){
                            continue;
                        }
                        hotelPicResDto.setpId(resId);
                        resId=saveHotelPicRes(hotelPicResDto);
                        if (resId==null){
                            continue;
                        }
                        EHotelPicDto hotelPicDto =new EHotelPicDto();
                        hotelPicDto.setTypeCode(typeCode);
                        hotelPicDto.setHotelId(hotel.getId().longValue());
                        hotelPicDto.setResId(resId);
                        hotelPicDto.setMainShow(0);
                        hotelPicDto.setValid(true);
                        hotelPicDto.setCreateBy("手动同步");
                        hotelPicDto.setCreateTime(new Date());
                        hotelPicMapper.insertEHotelPic(hotelPicDto);
                    }
                }
            }
        }
        logger.info(String.format("====================init PMS hotelPic sync job >> hotelPicSync end===================="));
        return;
    }
    public void roomTypeInfoPicSync() {
        TRoomTypeInfoDto roomTypeInfoDto=new TRoomTypeInfoDto();
        Integer count=roomTypeInfoMapper.countTRoomTypeInfo(roomTypeInfoDto);
        if (count<=0){
            return;
        }
        logger.info(String.format("====================init PMS roomTypeInfoPic sync job >> roomTypeInfoPicSync begin===================="));
        logger.info(String.format("====================init PMS roomTypeInfoPic sync job >> roomTypeInfoPicSync roomTypeInfo size="+count+"===================="));
        Integer pageSize=100;//100
        Integer pageNum=count/pageSize;
        for (int i=0;i<=pageNum;i++){
            roomTypeInfoDto.setPageIndex(i);
            roomTypeInfoDto.setPageSize(pageSize);
            logger.info(String.format("====================init PMS roomTypeInfoPic sync job >> roomTypeInfoPicSync pageIndex=" + i + "===================="));
            List<TRoomTypeInfo>roomTypeInfos=roomTypeInfoMapper.queryTRoomTypeInfo(roomTypeInfoDto);
            if (CollectionUtils.isEmpty(roomTypeInfos)){
                continue;
            }
            for (TRoomTypeInfo roomTypeInfo:roomTypeInfos){
                if (StringUtils.isEmpty(roomTypeInfo.getPics())){
                    continue;
                }
                logger.info(String.format("====================init PMS roomTypeInfoPic sync job >> roomTypeInfoPicSync roomTypeId=" + roomTypeInfo.getRoomTypeId()+ "===================="));
                TRoomType roomType=roomTypeMapper.findTRoomTypeById(roomTypeInfo.getRoomTypeId());

                List<PicJsonCalss> picJsonCalsses=getPicUrl(roomTypeInfo.getPics());

                for (PicJsonCalss picJsonCalss:picJsonCalsses){
                    if (picJsonCalss.getName()==null||picJsonCalss.getPic()==null){
                        continue;
                    }
                    String typeCode=HotelPicEnum.getByCode(picJsonCalss.getName(),2).getId();
                    for (String picUrl:picJsonCalss.getPic()){
                        EHotelPicResDto hotelPicResDto =new EHotelPicResDto();
                        hotelPicResDto.setTypeCode(typeCode);
                        hotelPicResDto.setUrl(picUrl);
                        hotelPicResDto.setStateCode(HMSStatusEnum.PASS.getCode());
                        hotelPicResDto.setHotelId(roomType.getThotelId().longValue());
                        Long resId=saveHotelPicRes(hotelPicResDto);
                        if (resId==null){
                            continue;
                        }
                        hotelPicResDto.setpId(resId);
                        resId=saveHotelPicRes(hotelPicResDto);
                        if (resId==null){
                            continue;
                        }
                        EHotelPicDto hotelPicDto =new EHotelPicDto();
                        hotelPicDto.setTypeCode(typeCode);
                        hotelPicDto.setHotelId(roomType.getThotelId().longValue());
                        hotelPicDto.setRoomtypeId(roomType.getId().longValue());
                        hotelPicDto.setResId(resId);
                        hotelPicDto.setMainShow(0);
                        hotelPicDto.setValid(true);
                        hotelPicDto.setCreateBy("手动同步");
                        hotelPicDto.setCreateTime(new Date());
                        hotelPicMapper.insertEHotelPic(hotelPicDto);
                    }
                }
            }
        }
        logger.info(String.format("====================init PMS roomTypeInfoPic sync job >> roomTypeInfoPicSync end===================="));
        return;
    }
    public List<PicJsonCalss> getPicUrl(String hotelPic) {
        List<PicJsonCalss> pics=new ArrayList<PicJsonCalss>();
        List<Object> objects=JsonUtils.jsonToList(hotelPic);
        try {
            for (Object obj:objects){
                if (obj==null){
                    continue;
                }
                Map<String,String> picMaps=JsonUtils.jsonToMap(obj.toString());
                PicJsonCalss picJsonCalss = new PicJsonCalss();
                for (String key:picMaps.keySet()) {
                    if ("name".equals(key)) {
                        picJsonCalss.setName(picMaps.get(key));
                    }else{
                        System.out.println(picMaps.get(key));
                        List<Object> urls=JsonUtils.jsonToList(picMaps.get(key).toString());
                        List<String> urlStrings=new ArrayList<String>();
                        for (Object url:urls) {
                            if (url==null){
                                continue;
                            }
                            Map<String,String> urlMaps=JsonUtils.jsonToMap(url.toString());
                            for (String url1:urlMaps.keySet())
                            {
                                urlStrings.add(urlMaps.get(url1));
                            }
                        }
                        picJsonCalss.setPic(urlStrings);
                    }

                }
                pics.add(picJsonCalss);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return pics;
    }

    public Long saveHotelPicRes(EHotelPicResDto bean){
        EHotelPicResDto hotelPicRes=new EHotelPicResDto();
        hotelPicRes.setTypeCode(bean.getTypeCode());
        hotelPicRes.setUrl(bean.getUrl());
        hotelPicRes.setpId(bean.getpId());
        hotelPicRes.setStateCode(bean.getStateCode());
        hotelPicRes.setHotelId(bean.getHotelId());
        hotelPicRes.setValid(true);
        hotelPicRes.setCreateTime(new Date());
        hotelPicRes.setCreateBy("手动同步");
        hotelPicResMapper.insertEHotelPicRes(hotelPicRes);
        return hotelPicRes.getId();
    }
}
