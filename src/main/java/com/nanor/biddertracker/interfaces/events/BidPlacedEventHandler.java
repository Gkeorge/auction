package com.nanor.biddertracker.interfaces.events;

import com.nanor.bidcapture.domain.model.events.BidPlaced;
import com.nanor.biddertracker.application.internal.businessusecases.TrackBidder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Event Handler for the Bid Placed Event that the Bidder Tracker is interested in
 */
@Service
@RequiredArgsConstructor
public class BidPlacedEventHandler {

    private final TrackBidder trackBidder;

    @EventListener(classes = BidPlaced.class)
    void track(BidPlaced bidPlaced) {
        trackBidder.trackBidderActivity(bidPlaced);
    }

}
