package com.nanor.auction.infrastructure.repositories.jpa;

import com.nanor.auction.domain.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
