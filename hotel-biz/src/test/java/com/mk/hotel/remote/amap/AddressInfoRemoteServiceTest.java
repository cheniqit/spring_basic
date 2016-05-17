package com.mk.hotel.remote.amap;

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
        String latLocation = "29.58339000";
        String longLocation = "106.49745200";
        AddressByLocationResponse addressByLocationResponse = addressInfoRemoteService.findAddressByLocation(latLocation, longLocation);
        Assert.assertNotNull(addressByLocationResponse);
        Assert.assertNotNull(addressByLocationResponse.getInfocode());
        Assert.assertEquals("OK", addressByLocationResponse.getInfo());
    }


    @Test
    public void testFindTownCodeByLocation() throws Exception {
        String latLocation = "29.55476000";
        String longLocation = "106.57329600";
        String townCode = addressInfoRemoteService.findTownCodeByLocation(latLocation, longLocation);
        Assert.assertEquals("500103003", townCode);
    }
}