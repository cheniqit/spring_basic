package com.mk.taskfactory.common.exception;

/**
 * Created by Thinkpad on 2015/11/19.
 */
public class HotelException extends Exception{

    public HotelException(String message) {
        super(message);
    }

    public HotelException(String message, Throwable cause) {
        super(message, cause);
    }
}
