package com.nanor.bidcapture.application.commandservices;

import com.nanor.bidcapture.domain.model.BidCapture;
import com.nanor.bidcapture.domain.model.Offer;
import com.nanor.bidcapture.domain.model.events.BidPlaced;
import com.nanor.bidcapture.infrastructure.repositories.BidCaptureRepository;
import com.nanor.shared.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;


@Service
@RequiredArgsConstructor
public class BidOnAuction {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final BidCaptureRepository bidCaptureRepository;

    /**
     * Service command method handler to receive an offer for a bidder
     */
    public void bid(Long auctionId, Long bidder, Money bid, ZonedDateTime timeOfBid) {
        BidCapture bidCapture = bidCaptureRepository.findByAuctionId(auctionId);
        BidPlaced bidPlaced = bidCapture.placeBidFor(new Offer(bidder, bid, timeOfBid));
        applicationEventPublisher.publishEvent(bidPlaced);
    }

}
