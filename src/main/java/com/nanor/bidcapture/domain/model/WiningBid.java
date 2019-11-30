package com.nanor.bidcapture.domain.model;

import com.nanor.shared.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class WiningBid {

    private Long bidder;

    private Money bid;

    private ZonedDateTime timeOfBid;

}
