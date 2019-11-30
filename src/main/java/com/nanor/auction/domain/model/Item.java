package com.nanor.auction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.UUID;

@NoArgsConstructor
@Embeddable
@Getter
@AllArgsConstructor
public class Item {

    private UUID id;

    private String name;

}
