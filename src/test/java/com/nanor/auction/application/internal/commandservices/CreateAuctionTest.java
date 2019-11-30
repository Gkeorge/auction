package com.nanor.auction.application.internal.commandservices;

import com.nanor.TestLifecycleLogger;
import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.auction.domain.model.Auction;
import com.nanor.auction.domain.model.AuctionStatus;
import com.nanor.auction.domain.model.Item;
import com.nanor.auction.infrastructure.repositories.jpa.AuctionRepository;
import com.nanor.shared.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAuctionTest implements TestLifecycleLogger {

    @Mock
    private AuctionRepository auctionRepository;

    private CreateAuction createAuction;

    @BeforeEach
    void setup() {
        createAuction = new CreateAuction(auctionRepository);
    }

    @Test
    @DisplayName("Create a new auction")
    void create() {

        //setup
        NewAuctionRequest auctionRequest = new NewAuctionRequest(ZonedDateTime.now().minusDays(1),
                ZonedDateTime.now(),
                new Item(UUID.randomUUID(), "BOSE"),
                new Money(BigDecimal.TEN));

        Auction auction = new Auction();
        auction.setAuctionId(1l);
        auction.setEndTime(ZonedDateTime.now());
        auction.setReserve(new Money(BigDecimal.TEN));
        auction.setStartTime(ZonedDateTime.now().minusDays(1));
        auction.setStatus(AuctionStatus.IN_PROGRESS);

        given(auctionRepository.save(any(Auction.class))).willReturn(auction);

        //execute
        Long auctionId = createAuction.create(auctionRequest);

        //verify
        then(auctionRepository).should(times(1)) .save(any(Auction.class));
        then(auctionRepository).shouldHaveNoMoreInteractions();

        //assert
        assertAll("Auction",
                () -> assertThat(auctionId).isNotNull(),
                () -> assertThat(auctionId).isEqualTo(1l));

    }
}