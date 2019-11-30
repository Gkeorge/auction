package com.nanor.auction.interfaces.rest;

import com.nanor.auction.application.internal.commandservices.CreateAuction;
import com.nanor.auction.interfaces.rest.dto.AuctionResource;
import com.nanor.auction.interfaces.rest.transform.CreateAuctionDTOAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final CreateAuction createAuctionService;

    @PostMapping
    ResponseEntity<?> createAuction(@RequestBody AuctionResource auctionResource) {
        Long auctionId = createAuctionService.create(CreateAuctionDTOAssembler.toCommandFromDTO(auctionResource));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(AuctionController.class).slash(auctionId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
