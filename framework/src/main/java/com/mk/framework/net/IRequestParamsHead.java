package com.mk.framework.net;

import org.apache.http.Header;

import java.util.Map;

public interface IRequestParamsHead {

     Header[] getHeaders(Map<String, Object> params);

}
