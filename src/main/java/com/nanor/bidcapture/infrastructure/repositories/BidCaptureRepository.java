package com.nanor.bidcapture.infrastructure.repositories;

import com.nanor.bidcapture.domain.model.BidCapture;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BidCaptureRepository {

    private Map<Long, BidCapture> cache = new ConcurrentHashMap<>();

    public BidCapture findByAuctionId(Long auctionId) {
        return cache.get(auctionId);
    }

    public void add(BidCapture bidCapture) {
        cache.put(bidCapture.getAuctionId(), bidCapture);
    }
}
