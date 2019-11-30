package com.nanor.auction.domain.model;

import com.nanor.TestLifecycleLogger;
import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.shared.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuctionTest implements TestLifecycleLogger {

    @Test
    @DisplayName("Creates a new auction")
    void startAnAuction() {
        //setup
        NewAuctionRequest newAuctionRequest = new NewAuctionRequest(ZonedDateTime.now().minusDays(1),
                ZonedDateTime.now(),
                new Item(UUID.randomUUID(), "BOSE"),
                new Money(BigDecimal.TEN));

        //execute
        Auction auction = Auction.startAnAuction(newAuctionRequest);

        //assert
        assertAll("", () -> assertThat(auction.getEndTime()).isEqualTo(auction.getEndTime()),
                () -> assertThat(auction.getStartTime()).isEqualTo(auction.getStartTime()),
                () -> assertThat(auction.getReserve()).isEqualTo(newAuctionRequest.getReserve()),
                () -> assertThat(auction.getStatus()).isEqualTo(AuctionStatus.IN_PROGRESS));
    }

    @Test
    @DisplayName("Throws an exception when auction start time is after end time")
    void shouldThrowExceptionWhenCreatingAnAuctionWithInvalidDates() {
        //setup
        NewAuctionRequest newAuctionRequest = new NewAuctionRequest(ZonedDateTime.now().plusDays(1),
                ZonedDateTime.now(),
                new Item(UUID.randomUUID(), "BOSE"),
                new Money(BigDecimal.TEN));

        //execute
        assertThrows(IllegalArgumentException.class, () ->
                Auction.startAnAuction(newAuctionRequest), "Start time cannot be after End time");

    }
}