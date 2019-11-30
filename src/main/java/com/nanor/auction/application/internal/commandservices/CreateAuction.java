package com.nanor.auction.application.internal.commandservices;

import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.auction.domain.model.Auction;
import com.nanor.auction.infrastructure.repositories.jpa.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateAuction {

    private final AuctionRepository auctionRepository;

    /**
     * Service Command method to start an auction session
     *
     */
    public Long create(NewAuctionRequest startAuction) {
        Auction auction = Auction.startAnAuction(startAuction);
        return auctionRepository.save(auction).getAuctionId();
    }



}

