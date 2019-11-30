package com.nanor.bidcapture.domain.model.events;

import com.nanor.shared.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BidPlaced {

    private Long auctionId;

    private Long bidder;

    private Money bid;

    private ZonedDateTime timeOfBid;
}
