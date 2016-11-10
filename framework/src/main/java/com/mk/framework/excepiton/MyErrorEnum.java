package com.mk.framework.excepiton;


/**
 * Created by huangjie on 16/10/11.
 */
public enum MyErrorEnum {

    /**
     * 常规错误
     */
    COMMON_SIGN_ERROR(-20000, "签名验证错误"),
    COMMON_PARAMS_IS_NULL(-20001, "参数为空"),



    /**
     * 渠道 channel
     */
    CHANNEL_CODE_IS_NULL(-20100, "code参数为空"),
    CHANNEL_TYPE_IS_NULL(-20101, "type参数为空"),
    CHANNEL_PWD_IS_NULL(-20102, "pwd参数为空"),
    CHANNEL_NAME_IS_NULL(-20103, "name参数为空"),
    CHANNEL_CONFIG_VALUE_IS_NULL(-20104, "渠道销售配置参数为空"),
    CHANNEL_CODE_EXIST(-20105, "已经存在相同code的渠道商"),
    CHANNEL_CODE_LENGTH_ERROR(-20106, "code的长度应该大于3位小于10位"),
    CHANNEL_CODE_CONSIST_ERROR(-20107, "code应该为英文字母或者数字"),
    CHANNEL_CODE_NOT_EXIST(-20108, "根据code没有找到对应的渠道商"),
    CHANNEL_TYPE_ERROR(-20109, "type错误,渠道销售模式错误"),
    CHANNEL_MULTI_SALE_TYPE(-20110, "渠道对应的销售类型有多个"),
    CHANNEL_CONFIG_VALUE_ERROR(-20111, "渠道销售配置错误"),
    CHANNEL_IS_VALID_ERROR(-20112, "isValid 只能是'T' 或 'F'"),
    CHANNEL_HOTEL_NOT_CONFIG(-20113, "渠道未配置该酒店"),
    CHANNEL_INFO_ERROR(-20114, "渠道商信息错误"),
    CHANNEL_INFO_IS_NULL(-20115, "渠道商信息为空"),
    CHANNEL_CODE_ERROR(-20116, "otaCode错误 或 失效"),

    /**
     * 酒店 hotel
     */
    HOTEL_INFO_ERROR(-20200, "酒店信息错误"),
    HOTEL_ID_NOT_EXIST(-20201, "参数错误,根据酒店id没有找到对应的酒店信息"),
    HOTEL_ID_IS_NULL(-20202, "酒店id为空"),
    HOTEL_FACILITY_DTO_LIST_IS_NULL(-20203, "hotelFacilityDtoList 不可为空"),
    HOTEL_FANG_ID_ERROR(-20204, "hotelFacilityDto.getFangHotelId() 错误"),

    /**
     * 房型 room
     */
    ROOM_NOT_FOUND(-20300, "无房型信息"),
    ROOM_PRICE_NOT_FOUND(-20301, "房型价格信息不存在"),
    ROOM_BOOK_PRICE_ERROR(-20302, "预定房间价格信息错误"),
    ROOM_TYPE_ID_IS_NULL(-20303, "roomTypeId参数为空"),
    ROOM_TYPE_NOT_CONFIG(-20304, "没有配置房型数据"),
    ROOM_TYPE_CONFIG_ERROR(-20305, "配置房型数据错误"),
    ROOM_INFO_ERROR(-20306, "房型信息错误"),
    ROOM_TYPE_ERROR(-20307, "房型错误 或 失效"),

    /**
     * 订单 order
     */
    ORDER_CREATE_PMS_ERROR(-20400, "创建pms订单错误"),
    ORDER_PARAMS_ERROR(-20401, "参数错误"),
    ORDER_DETAIL_IS_BLANK(-20402, "参数错误,订单明细为空"),
    ORDER_HOTEL_ID_IS_NULL(-20403, "参数错误,酒店id为空"),
    ORDER_NUM_IS_NULL(-20404, "参数错误,otaOrderNo为空"),
    ORDER_AMOUNT_IS_NULL(-20405, "参数错误,订单金额为空"),
    ORDER_DETAIL_DATE_ERROR(-20406, "参数错误,订明细入住时间和入住天数不一致"),
    ORDER_TOTAL_AMOUNT_ERROR(-20407, "参数错误,订单总金额和订单明细金额总和不一致"),
    ORDER_NO_NOT_EXIST(-20408, "参数错误,根据参数没有找到对应的订单"),
    ORDER_STATUS_CANCELLED(-20409, "订单已经是取消状态"),
    ORDER_STATUS_CHECKIN(-20410, "订单已经是入住状态不能被取消"),
    ORDER_STATUS_REFUSED(-20411, "订单已经是已拒绝状态不能被取消"),
    ORDER_MULTI_ON_ONE_ID(-20412, "订单号存在多个订单信息"),
    ORDER_NO_IS_NULL(-20413, "orderNo为空"),
    ORDER_CREATE_FAIL(-20414, "创建订单失败"),
    ORDER_UPDATE_ERROR(-20415, "订单更新失败"),
    ORDER_PRICE_CANNOT_CHANGE(-20416, "涉及到价格相关的信息不能被更改"),
    ORDER_CANCEL_PMS_FAIL(-20417, "取消pms订单失败"),
    ORDER_PARAMS_IS_NULL(-20418, "参数为空"),
    ORDER_PAY_MODE_NOT_SUPPORT(-204129, "订单支付模式不支持,只支持0,3两种模式"),
    ;


    private final Integer code;
    private final String message;

    private MyErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static MyErrorEnum findByCode(Integer code) {
        for (MyErrorEnum value : MyErrorEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }

    public MyException getMyException() {
        return new MyException(this.code, this.message);
    }
}
