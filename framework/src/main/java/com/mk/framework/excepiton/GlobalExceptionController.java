package com.mk.framework.excepiton;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * 系统例外处理
 *
 * @author nolan.
 */
@ControllerAdvice
public class GlobalExceptionController {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(MyException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(MyException ex) {
        Map<String, Object> errorMap = Maps.newHashMap();
        errorMap.put("success", "F");
        errorMap.put("errorCode", ex.getErrorCode());
        errorMap.put("errorMessage", ex.getMessage());
        GlobalExceptionController.logger.error("异常::" + ex.getLocalizedMessage(), ex);
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        GlobalExceptionController.logger.error(e.getMessage(), e);

        Map<String, Object> errorMap = Maps.newHashMap();
        errorMap.put("errorMessage", e.getMessage());

        return new ResponseEntity<>(errorMap, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.OK);
    }

}