package com.mk.taskfactory.common.exception;

/**
 * Created by Thinkpad on 2015/11/19.
 */
public class HotelRuntimeException extends RuntimeException{

    public HotelRuntimeException(String message) {
        super(message);
    }

    public HotelRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
