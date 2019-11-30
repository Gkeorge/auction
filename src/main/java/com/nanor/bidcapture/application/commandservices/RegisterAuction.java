package com.nanor.bidcapture.application.commandservices;

import com.nanor.auction.domain.events.AuctionStarted;
import com.nanor.bidcapture.domain.model.BidCapture;
import com.nanor.bidcapture.infrastructure.repositories.BidCaptureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAuction {

    private final BidCaptureRepository bidCaptureRepository;

    /**
     * Service command method to register an auction that has started
     */
    public void registerAuction(AuctionStarted auctionStarted) {
        bidCaptureRepository.add(new BidCapture(auctionStarted.getAuctionId(), auctionStarted.getStartedTime(),
                auctionStarted.getEndsAt(), auctionStarted.getReserve()));
    }
}
