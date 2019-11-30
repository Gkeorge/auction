package com.nanor.auction.domain.model;

import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.auction.domain.events.AuctionStarted;
import com.nanor.auction.exception.CannotEndAuctionException;
import com.nanor.shared.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auction extends AbstractAggregateRoot<Auction> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    @Embedded
    private Item item;

    @Embedded
    private Money reserve;

    private AuctionStatus status;

    private Auction(NewAuctionRequest newAuctionRequest) {
        this.startTime = newAuctionRequest.getStartTime();
        this.endTime = newAuctionRequest.getEndTime();
        this.item = newAuctionRequest.getItem();
        this.reserve = newAuctionRequest.getReserve();
        this.status = AuctionStatus.IN_PROGRESS;
        addDomainEvent(new AuctionStarted(1L, startTime, endTime, reserve));
    }

    public static Auction startAnAuction(NewAuctionRequest newAuctionRequest) {
        if (newAuctionRequest.getStartTime().isAfter(newAuctionRequest.getEndTime()))
            throw new IllegalArgumentException("Start time cannot be after End time");
        return new Auction(newAuctionRequest);
    }

    public void endAuction() {
        if (this.endTime.isBefore(ZonedDateTime.now()))
            this.status = AuctionStatus.OVER;
        else
            throw new CannotEndAuctionException("Auction already over");
    }

    /**
     * Method to register the event
     *
     * @param event
     */
    public void addDomainEvent(Object event) {
        registerEvent(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(auctionId, auction.auctionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId);
    }
}
