package com.mk.hotel.remote.amap;

import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chenqi on 16/5/12.
 */
public class AddressInfoRemoteServiceTest {
    AddressInfoRemoteService addressInfoRemoteService = new AddressInfoRemoteService();

    @Test
    public void testFindAddressByLocation() throws Exception {
        String logLocation = "106.57329600";
        String lanLocation = "29.55476000";
        AddressByLocationResponse addressByLocationResponse = addressInfoRemoteService.findAddressByLocation(logLocation, lanLocation);
        Assert.assertNotNull(addressByLocationResponse);
        Assert.assertNotNull(addressByLocationResponse.getInfocode());
        Assert.assertEquals("OK", addressByLocationResponse.getInfo());
    }


}