package com.nanor.auction.interfaces.rest;

import com.nanor.auction.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class TestAuctionResource {

    private String startTime;

    private String endTime;

    private Item item;

    private Integer reserve;
}
