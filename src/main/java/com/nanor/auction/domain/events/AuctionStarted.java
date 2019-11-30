package com.nanor.auction.domain.events;

import com.nanor.shared.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AuctionStarted {

    private Long auctionId;

    private ZonedDateTime startedTime;

    private ZonedDateTime endsAt;

    private Money reserve;
}
