package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface UploadRoomListService {
    public String readExcelContent(InputStream is, List<TRoomSaleConfigDto> resultList);
    public String saveRoomSaleConfigList(InputStream is);
}
