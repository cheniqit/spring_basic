package com.mk.framework.net;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirinli on 16/5/10.
 */
public class HeaderConfig {
    private List<Header> deviceList = new ArrayList<>();

    public static HeaderConfig custom() {
        return new HeaderConfig();
    }

    public HeaderConfig add(String key, String name) {
        BasicHeader header = new BasicHeader(key, name);

        deviceList.add(header);

        return this;
    }

    public HeaderConfig add(Header[] headers) {
        if ( headers != null ) {
            for (Header header : headers) {
                deviceList.add(header);
            }
        }

        return this;
    }

    public Header[] toArray() {
        Header[] headers = new Header[deviceList.size()];

        for (int i = 0; i < deviceList.size(); i++) {
            headers[i] = deviceList.get(i);
        }

        return headers;
    }
}
