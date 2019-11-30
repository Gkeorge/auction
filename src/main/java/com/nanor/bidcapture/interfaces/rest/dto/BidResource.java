package com.nanor.bidcapture.interfaces.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class BidResource {

    private Long auctionId;

    private Long bidder;

    private BigDecimal bid;

    private ZonedDateTime timeOfBid;
}
