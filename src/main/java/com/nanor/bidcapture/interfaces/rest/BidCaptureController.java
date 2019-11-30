package com.nanor.bidcapture.interfaces.rest;

import com.nanor.bidcapture.application.commandservices.BidOnAuction;
import com.nanor.bidcapture.interfaces.rest.dto.BidResource;
import com.nanor.shared.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/placebid")
public class BidCaptureController {

    private final BidOnAuction bidOnAuction;

    /**
     * POST method to place a bid
     *
     * @param bidResource
     */
    @PostMapping
    public void placeBid(@RequestBody BidResource bidResource) {
        bidOnAuction.bid(bidResource.getAuctionId(), bidResource.getBidder(),
                new Money(bidResource.getBid()), bidResource.getTimeOfBid());
    }


}
