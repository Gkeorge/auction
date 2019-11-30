package com.nanor.bidcapture.interfaces.events;

import com.nanor.auction.domain.events.AuctionStarted;
import com.nanor.bidcapture.application.businessusecases.RegisterAuction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class AuctionStartedEventHandler {

    private final RegisterAuction registerAuction;

    @TransactionalEventListener
    void receiveEvent(AuctionStarted auctionStartedEvent) {
        registerAuction.registerAuction(auctionStartedEvent);
    }
}
