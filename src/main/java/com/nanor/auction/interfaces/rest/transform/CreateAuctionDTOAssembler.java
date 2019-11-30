package com.nanor.auction.interfaces.rest.transform;

import com.nanor.auction.domain.commands.NewAuctionRequest;
import com.nanor.auction.domain.model.Item;
import com.nanor.auction.interfaces.rest.dto.AuctionResource;
import com.nanor.shared.Money;

import java.math.BigDecimal;

/**
 * Assembler class to convert the Auction Resource Data to the NewAuctionRequest Model
 */
public class CreateAuctionDTOAssembler {

    /**
     * Static method within the Assembler class
     *
     * @param auctionResource
     * @return NewAuctionRequest Model
     */
    public static NewAuctionRequest toCommandFromDTO(AuctionResource auctionResource) {
        return new NewAuctionRequest(auctionResource.getStartTime(), auctionResource.getEndTime(),
                auctionResource.getItem(), new Money(new BigDecimal(auctionResource.getReserve())));
    }
}
