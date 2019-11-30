package com.nanor.bidcapture.domain.model;

import com.nanor.bidcapture.domain.model.events.BidPlaced;
import com.nanor.bidcapture.exception.AuctionOverException;
import com.nanor.bidcapture.exception.BadBidException;
import com.nanor.shared.Money;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class BidCapture {

    private Long auctionId;
    private ZonedDateTime startedAt;
    private ZonedDateTime endsAt;
    private Money reserve;
    private WiningBid winingBid;

    public BidCapture(Long auctionId, ZonedDateTime startedAt, ZonedDateTime endsAt, Money reserve) {
        this.auctionId = auctionId;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.reserve = reserve;
    }

    public BidPlaced placeBidFor(Offer offer) {
        if (stillInProgress(offer.getTimeOfOffer())) {
            if (firstOffer())
                placeBidForTheFirst(offer);
            else
                placeBid(offer);
            return new BidPlaced(auctionId, offer.getBidder(),
                    offer.getBid(), offer.getTimeOfOffer());
        }
        throw new AuctionOverException();
    }

    private void placeBidForTheFirst(Offer offer) {
        if (offer.getBid().isGreaterThan(reserve))
            winingBid = new WiningBid(offer.getBidder(), offer.getBid(), offer.getTimeOfOffer());
        else
            throw new BadBidException();
    }

    private void placeBid(Offer offer) {
        if (offer.getBid().isGreaterThan(winingBid.getBid()))
            winingBid = new WiningBid(offer.getBidder(), offer.getBid(), offer.getTimeOfOffer());
        else
            throw new BadBidException();
    }

    private boolean firstOffer() {
        return winingBid == null;
    }

    private boolean stillInProgress(ZonedDateTime currentTime) {
        return endsAt.isAfter(currentTime);
    }

}
