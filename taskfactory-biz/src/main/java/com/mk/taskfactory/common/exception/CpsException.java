package com.mk.taskfactory.common.exception;

/**
 * Created by Thinkpad on 2015/11/19.
 */
public class CpsException extends RuntimeException{

    public CpsException(String message) {
        super(message);
    }

    public CpsException(String message, Throwable cause) {
        super(message, cause);
    }
}
