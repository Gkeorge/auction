package com.nanor.auction.interfaces.rest.dto;

import com.nanor.auction.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AuctionResource {

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Item item;

    private String reserve;
}
