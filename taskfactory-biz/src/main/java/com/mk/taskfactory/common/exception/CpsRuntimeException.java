package com.mk.taskfactory.common.exception;

/**
 * Created by Thinkpad on 2015/11/19.
 */
public class CpsRuntimeException extends RuntimeException{

    public CpsRuntimeException(String message) {
        super(message);
    }

    public CpsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
