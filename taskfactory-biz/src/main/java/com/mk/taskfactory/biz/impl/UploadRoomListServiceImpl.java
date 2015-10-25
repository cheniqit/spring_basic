package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleConfigService;
import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.UploadRoomListService;
import com.mk.taskfactory.api.dtos.TRoomDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadRoomListServiceImpl implements UploadRoomListService {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;

    public String saveRoomSaleConfigList(InputStream is) {
        List<TRoomSaleConfigDto> resultList = new ArrayList<TRoomSaleConfigDto>();
        String result = this.readExcelContent(is, resultList);

        for (TRoomSaleConfigDto dto : resultList) {
            TRoomSaleConfigDto paramDto = new TRoomSaleConfigDto();
            paramDto.setHotelId(dto.getHotelId());
            paramDto.setRoomTypeId(dto.getRoomTypeId());
            paramDto.setRoomId(dto.getRoomId());

            List<TRoomSaleConfigDto> dtoList = this.roomSaleConfigService.queryRoomSaleConfigByParams(paramDto);

            if (dtoList.isEmpty()) {
                this.roomSaleConfigService.saveRoomSaleConfig(dto);
            } else {
                TRoomSaleConfigDto dbDto = dtoList.get(0);
                dto.setId(dbDto.getId());
                this.roomSaleConfigService.updateRoomSaleConfig(dto);
            }
        }
        if (StringUtil.isEmpty(result)) {
            result = "导入成功";
        }
        return result;
    }
    public String readExcelContent(InputStream is, List<TRoomSaleConfigDto> resultList) {
        StringBuilder strError = new StringBuilder();

        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        //hotelId,roomId,roomTypeId,saleType,saleValue,costPrice,num,saleName,saleRoomTypeId,settleValue,settleType,valid,styleType,started,saleConfigInfoId
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);

            sheet = wb.getSheetAt(0);
            //
            int lastRowNum = sheet.getLastRowNum();

            //
            for (int i = 1; i <= lastRowNum;i++) {
                HSSFRow row = sheet.getRow(i);

                //
                Long hotelId = this.getLongCell(row.getCell(0));
                String roomType = this.getStringCell(row.getCell(1));
                String roomName = this.getCell(row.getCell(2));
                String startTime = this.getTimeCell(row.getCell(3));
                String endTime = this.getTimeCell(row.getCell(4));
                String startDate = this.getDateCell(row.getCell(5));
                String endDate = this.getDateCell(row.getCell(6));
                BigDecimal saleValue = new BigDecimal(this.getNumericCell(row.getCell(7)));
                Long num = this.getLongCell(row.getCell(8));
                Long type = this.getLongCell(row.getCell(9));

                //
                if (null == hotelId) {
                    strError.append(i + "行，hotelId为空").append("/n");
                    continue;
                }

                //roomType
                TRoomTypeDto roomTypeParam = new TRoomTypeDto();
                roomTypeParam.setThotelId(hotelId.intValue());
                roomTypeParam.setName(roomType);

                TRoomTypeDto roomTypeDto = null;
                try {
                    roomTypeDto = this.roomTypeService.findByName(roomTypeParam);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (null == roomTypeDto) {
                    strError.append(i + "行，房型错误").append("/n");
                    continue;
                }
                //room
                TRoomDto roomParam = new TRoomDto();
                roomParam.setRoomTypeId(roomTypeDto.getId());
                roomParam.setName(roomName);

                TRoomDto roomDto = null;
                try {
                    roomDto = this.roomService.queryRoomByName(roomParam);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //
                TRoomSaleConfigDto configDto = new TRoomSaleConfigDto();
                configDto.setHotelId(hotelId.intValue());
                configDto.setRoomTypeId(roomTypeDto.getId());
                if (null != roomDto) {
                    configDto.setRoomId(roomDto.getId());
                }
//                configDto.setStartTime(startTime);
//                configDto.setEndTime(endTime);
//                configDto.setStartDate(startDate);
//                configDto.setEndDate(endDate);
//                configDto.setSaleValue(saleValue.doubleValue());
//                configDto.setNum(num.intValue());
//                configDto.setType(type.intValue());

                if (null != resultList) {
                    resultList.add(configDto);
                }
            }
        } catch (IOException e) {
            strError.append(e.toString());
            e.printStackTrace();
        }
        return strError.toString();
    }

    private String getCell(HSSFCell cell) {
        if (null == cell) {
            return "";
        }
        int cellType = cell.getCellType();

        if (HSSFCell.CELL_TYPE_STRING == cellType) {
            return this.getStringCell(cell);
        } else if (HSSFCell.CELL_TYPE_NUMERIC == cellType) {
            Long longValue = this.getLongCell(cell);
            return String.valueOf(longValue);
        } else if (HSSFCell.CELL_TYPE_BOOLEAN == cellType) {

            return "";
        } else {
            return "";
        }
    }

    private String getStringCell(HSSFCell cell) {
        if (null == cell) {
            return "";
        }
        String strValue = cell.getStringCellValue();
        if (StringUtil.isEmpty(strValue)) {
            return "";
        } else {
            return strValue;
        }
    }

    private Double getNumericCell(HSSFCell cell) {
        if (null == cell) {
            return null;
        }
        Double value = cell.getNumericCellValue();
        return value;
    }

    private Long getLongCell(HSSFCell cell) {
        if (null == cell) {
            return null;
        }
        Double doubleValue = this.getNumericCell(cell);
        if (null == doubleValue) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(doubleValue);

        return bigDecimal.longValue();
    }

    private String getDateCell(HSSFCell cell) {
        if (null == cell) {
            return null;
        }
        Date value = cell.getDateCellValue();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(value);
    }

    private String getTimeCell(HSSFCell cell) {
        if (null == cell) {
            return null;
        }
        Date value = cell.getDateCellValue();

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        return format.format(value);
    }
}
