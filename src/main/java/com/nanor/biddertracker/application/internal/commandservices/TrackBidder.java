package com.nanor.biddertracker.application.internal.commandservices;

import com.nanor.bidcapture.domain.model.events.BidPlaced;
import com.nanor.biddertracker.domain.model.Bidder;
import com.nanor.biddertracker.infrastructure.repositories.jpa.BidderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackBidder {

    private final BidderRepository bidderRepository;

    /**
     * Service command method handler to track a bidder activity
     */
    public void trackBidderActivity(BidPlaced bidPlaced) {
        bidderRepository.save(new Bidder(bidPlaced.getAuctionId(), bidPlaced.getBidder(),
                bidPlaced.getBid(), bidPlaced.getTimeOfBid())); //just saving bidder detail for now
    }
}
