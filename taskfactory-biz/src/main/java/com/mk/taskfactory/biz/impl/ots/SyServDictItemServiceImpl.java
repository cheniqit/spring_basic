package com.mk.taskfactory.biz.impl.ots;

import com.mk.taskfactory.api.HotelPicSyncService;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.api.enums.HMSStatusEnum;
import com.mk.taskfactory.api.enums.HotelPicEnum;
import com.mk.taskfactory.api.ots.SyServDictItemService;
import com.mk.taskfactory.biz.mapper.ots.*;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.model.*;
import com.mk.taskfactory.model.ods.TRoomTypePriceDump;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SyServDictItemServiceImpl implements SyServDictItemService {
    @Autowired
    private SyServDictItemMapper syServDictItemMapper;

    public List<SyServDictItemDto> queryByParams(SyServDictItemDto bean){
        List<SyServDictItem> list = syServDictItemMapper.queryByParams(bean);
        if (CollectionUtils.isEmpty(list)){
            return  null;
        }
        List<SyServDictItemDto> resultList = new ArrayList<SyServDictItemDto>();

        for (SyServDictItem model : list) {
            resultList.add(buildDto(model));
        }
        return resultList;
    }
    private SyServDictItemDto buildDto(SyServDictItem bean) {
        if (bean==null){
            return new SyServDictItemDto();
        }
        SyServDictItemDto resultDto=new SyServDictItemDto();
        BeanUtils.copyProperties(bean, resultDto);
        return resultDto;
    }
}
