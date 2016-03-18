package com.mk.framework.proxy.http;

/**
 * Created by 振涛 on 2016/1/19.
 */
public class ThreadContext {

    public static final ThreadLocal<ProxyServer> PROXY_SERVER_THREAD_LOCAL = new ThreadLocal<>();

}
