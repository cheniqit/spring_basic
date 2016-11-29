package com.mk.common;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by chenqi on 16/11/24.
 */

@Component
public class PropertyUtils {

    @Value("${namesrvAddr}")
    public String namesrvAddr;

    @Value("${producerGroupName}")
    public String producerGroupName;

    @Value("${instanceName}")
    public String instanceName;

    @Value("${motanServiceGroup}")
    public String motanServiceGroup;
    
    @Value("${dev}")
    public boolean isDev;



}
