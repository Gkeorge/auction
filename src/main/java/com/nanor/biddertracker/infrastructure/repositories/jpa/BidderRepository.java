package com.nanor.biddertracker.infrastructure.repositories.jpa;

import com.nanor.biddertracker.domain.model.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidderRepository extends JpaRepository<Bidder, Long> {
}
