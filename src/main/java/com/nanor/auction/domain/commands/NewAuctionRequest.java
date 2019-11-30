package com.nanor.auction.domain.commands;

import com.nanor.auction.domain.model.Item;
import com.nanor.shared.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class NewAuctionRequest {

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Item item;

    private Money reserve;

}
