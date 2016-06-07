package com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class ProxyInns {
    private Integer innId;
    private List<PricePatterns> pricePatterns;

    public Integer getInnId() {
        return innId;
    }

    public void setInnId(Integer innId) {
        this.innId = innId;
    }

    public List<PricePatterns> getPricePatterns() {
        return pricePatterns;
    }

    public void setPricePatterns(List<PricePatterns> pricePatterns) {
        this.pricePatterns = pricePatterns;
    }
}
