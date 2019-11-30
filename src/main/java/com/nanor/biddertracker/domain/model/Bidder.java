package com.nanor.biddertracker.domain.model;

import com.nanor.shared.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Bidder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long auctionId;

    private Long bidderId;

    private Money bid;

    private ZonedDateTime timeOfBid;

    public Bidder(Long auctionId, Long bidderId, Money bid, ZonedDateTime timeOfBid) {
        this.auctionId = auctionId;
        this.bidderId = bidderId;
        this.bid = bid;
        this.timeOfBid = timeOfBid;
    }
}
